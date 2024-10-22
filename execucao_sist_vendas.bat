@echo off
set CLASSPATH=./Sistema_de_Vendas.jar;./sqlite-jdbc-3.46.1.3.jar;./produtos.db;./saída.db
java -cp %CLASSPATH% Main
pause