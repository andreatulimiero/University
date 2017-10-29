select p1, p2
from programmatore p1, programmatore p2
where (
    	select count(codice)
    	from programmatore natural join autore natural join programma
    	where programmatore = p1
    	group by programmatore
	  ) = 
      (
    	select count(codice)
    	from programmatore natural join autore natural join programma
    	where programmatore = p2
    	group by programmatore
	  ) 
      and p1 != p2 and p1 < p2