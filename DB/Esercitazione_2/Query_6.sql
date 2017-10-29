select categoria, codice, anno
from programmatore p natural join autore natural join programma r
where linguaggio != 'Java'
      and not exists (
		      select * from programmatore natural join autore natural join programma 
		      where codice = p.codice and linguaggio != 'Java' and anno < r.anno
		      )
group by categoria, codice, anno
having categoria = 10