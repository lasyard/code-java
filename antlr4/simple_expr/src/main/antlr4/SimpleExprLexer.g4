lexer grammar SimpleExprLexer;

@header {
package io.github.lasyard.code.antlr4.simple.expr;
}

ID      : [a-zA-Z]+ ;
INT     : [0-9]+ ;
NEWLINE : ';' | ('\r'? '\n') ;
WS      : [ \t]+ -> skip ;

MUL     : '*' ;
DIV     : '/' ;
ADD     : '+' ;
SUB     : '-' ;

LPAR    : '(' ;
RPAR    : ')' ;

ASSIGN  : '=' ;
