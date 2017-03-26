#ifndef LABIRINTO_H
#define	LABIRINTO_H

#ifdef	__cplusplus
extern "C" {
#endif

typedef void labirinto;

#define LABIRINTO_PIENA 1
#define LABIRINTO_VUOTA 0

#define LABIRINTO_FALSE 0
#define LABIRINTO_TRUE 1

extern labirinto * labirinto_new(int n);
extern void labirinto_setpiena(labirinto * lab, int r, int c);
extern int labirinto_risolvibile(labirinto * lab);
extern void labirinto_tostring(labirinto * lab, char * buffer, int buffer_size);
extern void labirinto_delete(labirinto * lab);

#ifdef	__cplusplus
}
#endif

#endif	/* LABIRINTO_H */

