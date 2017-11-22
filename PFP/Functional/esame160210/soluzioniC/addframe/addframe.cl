// =====================================================================
//  addframe.cl
// =====================================================================

//  Author:         (c) 2016 Camil Demetrescu
//  License:        See the end of this file for license information
//  Created:        Feb 10, 2016

//  Last changed:   $Date: 2016/02/10 --:--:-- $
//  Changed by:     $Author: demetres $
//  Revision:       $Revision: 1.00 $

#define IDX(x,y,w) ((y)*(w)+(x))

__kernel void addframe(__global unsigned char* in, 
                       __global unsigned char* out, 
                       int w, int h, 
                       unsigned char frame_grey, 
                       int frame_size) {

    int x = get_global_id(0);
    int y = get_global_id(1);

    int ow = w + 2*frame_size;
    int oh = h + 2*frame_size;
    
    if (x >= ow || y >= oh) return;
    
    if (x >= frame_size       && y >= frame_size && 
        x <  ow - frame_size  && y < oh - frame_size)
        out[IDX(x, y, ow)] = in[IDX(x-frame_size, y-frame_size, w)];
    else 
        out[IDX(x, y, ow)] = frame_grey;
}


// Copyright (C) 2016 Camil Demetrescu

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
