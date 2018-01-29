#include <stdio.h>
#include <immintrin.h>

#define MAT_SIZE 3

void matmul_ikj(const int **A, const int **B, int **C, size_t n) {
  int i, j, k;
  for (i = 0; i < n; i++)
    for (j = 0; j < n; j++)
      C[i][j] = 0;
  for (i = 0; i < n; i++)
    for (k = 0; k < n; k++)
      for (j = 0; j < n; j++)
        C[i][j] += A[i][k] * B[k][j];
}

void vectorial_matmul_ikj(const int **A, const int **B, int **C, size_t n) {
  int i, j, k;
  __m128i VEC_A, VEC_B;
  for (i = 0; i < n; i++)
    for (j = 0; j < n; j++)
      C[i][j] = 0;
  for (i = 0; i < n; i++)
    for (k = 0; k < n; k++) {
      for (j = 0; j < n; j += 4) {
        VEC_A = _mm_loadu_si128((const __m128i*) A[i] + k);
        VEC_B = _mm_loadu_si128((const __m128i*) B[k] + j);
        _mm_mullo_epi32(VEC_A, VEC_B);
        _mm_storeu_si128((__m128i*) C[i] + j, VEC_B);
      }
      for (; j < n; j++) C[i][j] += A[i][k] * B[k][j];
    }
}

int main(int argc, char **argv) {
  int** A = malloc(sizeof(int) * MAT_SIZE);
  int** B = malloc(sizeof(int) * MAT_SIZE);
  int** C = malloc(sizeof(int) * MAT_SIZE);
  int i, j;
  for(i = 0; i < MAT_SIZE; i++) {
    A[i] = malloc(sizeof(int) * MAT_SIZE);
    B[i] = malloc(sizeof(int) * MAT_SIZE);
    C[i] = malloc(sizeof(int) * MAT_SIZE);
    for (j = 0; j < MAT_SIZE; j++) {
      A[i][j] = (i+j) % 5;
      B[i][j] = (i+j) % 3;
    }
  }
  printf("-- Serial --\n");
  matmul_ikj((const int **)A, (const int **)B, (int **)C, (size_t) MAT_SIZE);
  for (i = 0; i < MAT_SIZE; i++) {
    for (j = 0; j < MAT_SIZE; j++) printf("%d " , C[i][j]);
    printf("\n");
  }

  printf("-- Vectorial --\n");
  vectorial_matmul_ikj((const int **)A, (const int **)B, (int **)C, (size_t)MAT_SIZE);
  for (i = 0; i < MAT_SIZE; i++){
    for (j = 0; j < MAT_SIZE; j++)
      printf("%d ", C[i][j]);
    printf("\n");
  }

  return 0;
}