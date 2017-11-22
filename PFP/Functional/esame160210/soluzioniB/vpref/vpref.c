#include "vpref.h"
#include <immintrin.h>

int vpref(const char* a, const char* b, int na, int nb) {
	int i = 0, n = na < nb ? na : nb;
    for (i=0; i+15<n; i+=16) {
        __m128i va  = _mm_loadu_si128((const __m128i*)(a+i));
        __m128i vb  = _mm_loadu_si128((const __m128i*)(b+i));
        __m128i res = _mm_cmpeq_epi8(va, vb);
        if (!_mm_test_all_ones(res)) return 0;
    }
    for (; i<n; i++) if (a[i] != b[i]) return 0;
    return 1;
}
