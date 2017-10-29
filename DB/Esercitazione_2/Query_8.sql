select p, linguaggio
from programmatore p natural join autore natural join programma prog
where
	(
    select count(id)
	from programmatore natural join autore natural join programma
	where linguaggio = prog.linguaggio and p = programmatore 
	group by programmatore, linguaggio
	)
    >=
    (
    select count(id)
	from programmatore natural join autore natural join programma
	where linguaggio != prog.linguaggio and p = programmatore
	group by programmatore, linguaggio
    )