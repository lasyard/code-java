grammar ArrayInit;

@header{
package io.github.lasyard.code.antlr4.array.init;
}

init    : '{' value (',' value)* '}'
        ;

value   : init
        | INT
        ;

INT     : [0-9]+ ;
WS      : [ \t\r\n]+ -> skip ;
