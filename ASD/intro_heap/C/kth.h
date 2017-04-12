#ifndef KTH_H
#define	KTH_H

#ifdef	__cplusplus
extern "C" {
#endif
    
typedef void kth;

kth * kth_new(int k);
void kth_delete(kth * d);
void kth_insert(kth * d, int key);
int kth_get(kth * d);
void kth_remove(kth * k);

#ifdef	__cplusplus
}
#endif

#endif	/* KTH_H */

