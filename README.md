# ValidaMega
Algoritmo para testar se um conjunto de bilhetes abrange todas as combinações possíveis da Mega-Sena

## Mega-Sena
A Mega-Sena é a maior modalidade lotérica do Brasil, sendo uma entre as dez modalidades atuais das loterias da Caixa (Caixa Econômica Federal), com sorteios ordinários três vezes por semana, além da Mega-Sena da Virada e outras modalidades de prêmios.

Para ganhar o prêmio máximo da Mega-Sena, denominado sena, é necessário obter coincidência entre seis dos números apostados e os seis números sorteados, de um total de seis dezenas (de um a sessenta), independentemente da ordem da aposta ou da ordem do sorteio.

Fonte: [Wikipedia](https://pt.wikipedia.org/wiki/Mega-Sena)

## O desafio
O Professor Daniel Nunes do canal do Youtube Tem Ciência publicou um [vídeo](https://www.youtube.com/watch?v=7ThAC-g4Fg0) propondo o seguinte problema:

Sabendo-se que cada bilhete com seis dezenas corresponde a uma chance em 50 milhões de ser sorteado, seriam necessários 50 milhões de bilhetes com seis dezenas para garantir com cem por cento de certeza o acerto das seis dezenas sorteadas, desconsiderando a viabilidade econômica.

Considerando-se que é possível comprar bilhetes com 6 a 20 dezenas e que bilhetes com mais dezenas contém um número maior de combinações, o desafio é determinar o menor número de bilhetes para abranger todas as combinações possíveis e garantir com cem por cento de certeza o acerto das seis dezenas sorteadas, desconsiderando a viabilidade econômica.

## Este algoritmo
A proposta deste algoritmo é testar um conjunto de bilhetes para verificar se abrange as 50.063.860 combinações possíveis.

## O arquivo megainviavel.dat

O arquivo megainviavel.dat deve conter um conjunto de bilhetes com 6 a 20 dezenas, se a quantidade de dezenas de algum bilhete estiver fora desta faixa é gerada uma exceção. Os bilhetes são codificados em 64 bits, sendo que o primeiro bit (bit 0) corresponde à primeira dezena "01", o segundo bit (bit 1) corresponde à segunda dezena "02" e assim por diante até o sexagésimo bit (bit 59). Se o bit possuir o valor 1, a dezena correspondente à posição do bit faz parte do bilhete, se o valor do bit for 0 a dezena não faz parte do bilhete.

## O teste
A classe ValidaMega utiliza os métodos da classe ValidaNúmeros para realizar um teste utilizando um conjunto composto por apenas um bilhete com as dezenas de 01 a 20, de forma que ao testar a combinação "01 02 03 04 05 21" é identificado que esta combinação não corresponde a 6 dezenas deste bilhete e gera uma exceção.

Este primeiro teste demonstra que se o conjunto de bilhetes não corresponder a uma das dezenas testadas será gerada uma exceção.

Posteriormente é gerado um novo teste usando o arquivo megainviavel.dat para identificar se os bilhetes codificados neste arquivo abrangem todas as combinações.

O arquivo megainviavel.dat possui as 24013 combinações de bilhetes que identifiquei que são suficientes para garantir 100% de acerto nas 6 dezesnas sorteadas, não importa quais sejam.



