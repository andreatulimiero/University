__kernel void decode(
    __global const char* key,
    __gloabl const char* str_in,
    __gloabl char* str_out,
    int m, 
    int n
    ) {
  int i = get_global_id(0);
  if (i >= n) return;
  str[i] -= key[i % m];
}