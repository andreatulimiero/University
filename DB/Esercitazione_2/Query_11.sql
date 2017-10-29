select distinct p1, p2
from programmatore p1 natural join autore natural join programma, programmatore p2
where 
      (
        select count(distinct programma.linguaggio)
		from programmatore natural join autore natural join programma
		where programmatore = p1
		group by nome
      ) = 
      (
          select count(distinct programma.linguaggio)
		  from programmatore natural join autore natural join programma
		  where programmatore = p2
		  group by nome
      )
      and p1 != p2 and p1 < p2