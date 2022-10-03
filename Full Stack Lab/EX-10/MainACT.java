package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView numberview,clear;
    Button no0,no1,no2,no3,no4,no5,no6,no7,no8,no9,del,add,sub,mul,div,equal;
    String s="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberview=(TextView) findViewById(R.id.number_view);
        clear=(TextView) findViewById(R.id.clear);
        no0=(Button) findViewById(R.id.no0);
        no1=(Button) findViewById(R.id.no1);
        no2=(Button) findViewById(R.id.no2);
        no3=(Button) findViewById(R.id.no3);
        no4=(Button) findViewById(R.id.no4);
        no5=(Button) findViewById(R.id.no5);
        no6=(Button) findViewById(R.id.no6);
        no7=(Button) findViewById(R.id.no7);
        no8=(Button) findViewById(R.id.no8);
        no9=(Button) findViewById(R.id.no9);
        add=(Button) findViewById(R.id.add);
        sub=(Button) findViewById(R.id.sub);
        mul=(Button) findViewById(R.id.mul);
        div=(Button) findViewById(R.id.div);
        equal=(Button) findViewById(R.id.equal);
        del=(Button) findViewById(R.id.del);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s="";
                numberview.setText(s);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.length()!=0) {
                    String x = s.substring(0, s.length() - 1);
                    s=x;
                    numberview.setText(s);
                }else{
                    s="";
                }
            }
        });

        no0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="0";
                numberview.setText(s);
            }
        });

        no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="1";
                numberview.setText(s);
            }
        });

        no2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="2";
                numberview.setText(s);
            }
        });

        no3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="3";
                numberview.setText(s);
            }
        });

        no4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="4";
                numberview.setText(s);
            }
        });

        no5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="5";
                numberview.setText(s);
            }
        });

        no6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="6";
                numberview.setText(s);
            }
        });

        no7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="7";
                numberview.setText(s);
            }
        });

        no8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="8";
                numberview.setText(s);
            }
        });

        no9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="9";
                numberview.setText(s);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="+";
                numberview.setText(s);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="-";
                numberview.setText(s);
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="*";
                numberview.setText(s);
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s+="/";
                numberview.setText(s);
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double x=eval(s);
                String x2=Double.toString(x);
                numberview.setText(x2);
            }
        });

    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

}