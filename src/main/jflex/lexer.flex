package com.rudyreyes.emuladorsql;
import java.io.*;
import java_cup.runtime.*;

%%

%public
%class LexerSQL
%cup
%line
%column

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }

%}

number = [0-9]
letter = [a-zA-Z]
LITERAL = ({number}+|"\""[^"\n]*"\"")
whitespace = [ \t\r]
newline = [\n]

%eofval{
    return symbol(ParserIdeSym.EOF);
%eofval}


%%

<YYINITIAL> EN { return new Symbol(ParserSQLSym.EN); }
<YYINITIAL> {LITERAL} EN { return new Symbol(ParserSQLSym.PATH, yytext()); }

"SELECCIONAR"                           { return symbol(ParserSQLSym.SELECCIONAR); }
"INSERTAR"                              { return symbol(ParserSQLSym.INSERTAR); }
"ACTUALIZAR"                            { return symbol(ParserSQLSym.ACTUALIZAR); }
"ELIMINAR"                              { return symbol(ParserSQLSym.ELIMINAR); }
{LITERAL}                               { return symbol(ParserSQLSym.LITERAL, yytext()); }
{FILTRAR}                               { return symbol(ParserSQLSym.FILTRAR }
"AND"                                   { return symbol(ParserSQLSym.OP_AND); }
"OR"                                    { return symbol(ParserSQLSym.OP_OR); }
({letter}|"_")({letter}|{number}|"_")*  { return symbol(ParserSQLSym.NOMBRECOLUMNA, yytext()); }
"*"                                     { return symbol(ParserSQLSym.ASTERISCO); }
","                                     { return symbol(ParserSQLSym.COMA); }
";"                                     { return symbol(ParserSQLSym.P_COMA); }
"="                                     { return symbol(ParserSQLSym.SIMB_IGUAL); }
"<"                                     { return symbol(ParserSQLSym.SIMB_MENOR); }
">"                                     { return symbol(ParserSQLSym.SIMB_MAYOR); }
"<="                                    { return symbol(ParserSQLSym.SIMB_MENOR_IGUAL); }
">="                                    { return symbol(ParserSQLSym.SIMB_MAYOR_IGUAL); }
"<>"                                    { return symbol(ParserSQLSym.SIMB_NO_IGUAL); }

{whitespace} {/* ignore */}
{newline} {/* ignore */}