// =====================================================================
//  main.c
// =====================================================================

//  Author:         (c) 2017 Camil Demetrescu
//  License:        See the end of this file for license information
//  Created:        Apr 10, 2017

//  Last changed:   $Date: 2017/04/10 --:--:-- $
//  Changed by:     $Author: demetres $
//  Revision:       $Revision: 1.00 $


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "clut.h"
#include "pgm.h"
#include "crop.h"

#define PROG_NAME "crop.cl"
#define IDX(x,y,w) ((y)*(w)+(x))


// ---------------------------------------------------------------------
// equal
// ---------------------------------------------------------------------
// compare two matrices

int equal(unsigned char* a, unsigned char* b, int w, int h) {
    int x, y, res = 1;
    for (x=0; x<w; x++)
        for (y=0; y<h; y++)
            if (a[IDX(x,y,w)] != b[IDX(x,y,w)]) {
                printf("a[%d,%d] - b[%d,%d] = %d\n", x, y, x, y,
                       a[IDX(x,y,w)]-b[IDX(x,y,w)]);
                res = 0;
            }
    return res;
}


// ---------------------------------------------------------------------
// crop_host
// ---------------------------------------------------------------------
// sequential CPU version executed on host

void crop_host(unsigned char* in,
               unsigned xc, unsigned yc,
               unsigned wc, unsigned hc,
               unsigned w,  unsigned h,
               unsigned char** out, double* t) {

    unsigned x, y;

    // allocate output matrix
    *out = malloc(wc*hc*sizeof(unsigned char));
    if (*out == NULL) exit((printf("out of host memory"), 1));

    // get initial time
    double start = clut_get_real_time();

    // scan pixels of input image
    for (x=0; x<wc; x++)
        for (y=0; y<hc; y++)
            (*out)[IDX(x, y, wc)] = in[IDX(xc+x, yc+y, w)];

    // get elapsed time
    *t = clut_get_real_time() - start;
}


// ---------------------------------------------------------------------
// run_test
// ---------------------------------------------------------------------
// perform resize test on input matrix

void run_test(unsigned char* in,
              unsigned xc, unsigned yc,
              unsigned wc, unsigned hc,
              unsigned w,  unsigned h,
              unsigned char** out,
              clut_device* dev, char* msg) {

    double t, td;

    unsigned char* out_ok;

    printf("Cropping %u %u %u %u...\n", xc, yc, wc, hc);

    // compute filter on host
//    crop_host(in, xc, yc, wc, hc, w, h, &out_ok, &t);
    crop_host(in, xc, yc, wc, hc, w, h, out, &t);

    // compute filter on device
//    crop(in, xc, yc, wc, hc, w, h, out, dev, &td);
    crop(in, xc, yc, wc, hc, w, h, &out_ok, dev, &td);

    // validate our results
    printf("%s\n", msg);
    printf("    CPU took %.3f msec\n", t*1E03);
    printf("    Device took %.3f msec\n", td*1E03);
    printf("    Correctness test: %s\n", equal(*out, out_ok, wc, hc) ?
										 "PASSED" : "FAILED");

    // cleanup
	free(out_ok);
}


// ---------------------------------------------------------------------
// main
// ---------------------------------------------------------------------
int main(int argc, char** argv) {

    clut_device     dev;                // device structure
    char*           in_path;			// input image pathname
    char*           out_path;			// output image pathname
    unsigned char*  in;                 // input image on host
    unsigned char*  out;                // output image on host
    unsigned        w, h;               // input image size (width, height)
    unsigned        xc, yc, wc, hc;     // output image coords and size
    int             err;                // misc vars

    // args count check
    if (argc < 3) {
        printf("Usage: %s <input-file> <output-file> <xc> <yc> <wc> <hc>\n", argv[0]);
        return 1;
    }

    // set parameters
    in_path    = argv[1];
    out_path   = argv[2];
    xc         = atoi(argv[3]);
    yc         = atoi(argv[4]);
    wc         = atoi(argv[5]);
    hc         = atoi(argv[6]);

    // open device
    clut_open_device(&dev, PROG_NAME);

    // load image file in pgm format
    printf("Open image file: %s\n", in_path);
    err = pgm_load(&in, &h, &w, in_path);
    if (err) clut_panic("failed to load input image file");
    printf("Loaded %dx%d image\n", w, h);

    // adjust params if needed
    xc = xc > w ? w : xc;
    yc = yc > h ? h : yc;
    wc = wc > w-xc ? w-xc : wc;
    hc = hc > h-yc ? h-yc : hc;

    // print device info
    clut_print_device_info(&dev);

    // run test
    run_test(in, xc, yc, wc, hc, w, h, &out, &dev, "Running test");

    // save output image
    printf("    Saving image: %s (%u x %u)\n", out_path, wc, hc);
    err = pgm_save(out, hc, wc, out_path);
    if (err) clut_panic("failed to save output image file");

    // cleanup
    free(in);
    free(out);
    clut_close_device(&dev);

    return 0;
}


// Copyright (C) 2017 Camil Demetrescu

// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.

// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.

// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
// USA
