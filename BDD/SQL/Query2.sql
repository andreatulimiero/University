SELECT nome, categoria
FROM programmatore natural join autore natural join programma
WHERE programma.linguaggio != 'Python'
ORDER BY nome