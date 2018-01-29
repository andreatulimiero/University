#include "clut.h"
#include <string.h>

#define KERNEL_NAME "decode"

void decode (const char* key, int m, char* str) {
  int err;
  cl_kernel kernel;
  cl_mem dkey;
  cl_mem din;
  cl_mem dout;
  clut_device dev;
  int n = strlen(n);
  
  clut_open_device(&dev, "decode.cl");
  clut_print_device_info(&dev);

  kernel = clCreateKernel(dev.program, KERNEL_NAME, &err);
  clut_check_err(err, "blah blah")

  dkey = clCreateBuffer(dev.context,
                        CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                        m * sizeof(char),
                        (void*) key, NULL);

  din = clCreateBuffer(dev.context,
                       CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR,
                       n * sizeof(char),
                       (void*) key, NULL);

  dout = clCreateBuffer(dev.context,
                       CL_MEM_READ_ONLY,
                       n * sizeof(char),
                       NULL, NULL);

  err  = clSetKernelArg(kernel, 0, sizeof(cl_mem), &dkey);
  err |= clSetKernelArg(kernel, 1, sizeof(cl_mem), &din);
  err |= clSetKernelArg(kernel, 2, sizeof(cl_mem), &dout);
  err |= clSetKernelArg(kernel, 3, sizeof(int), &m);
  err |= clSetKernelArg(kernel, 4, sizeof(int), &n);
  clut_check_err(err, "blah blah");

  clReleaseMemObject(dkey);
  clReleaseMemObject(din);
  clReleaseMemObject(dout);
  clut_close_device(&dev);
}

int main(int argc, char** argv) {
  decode("chiave", 10, "hhsjss")

  return 0;
}