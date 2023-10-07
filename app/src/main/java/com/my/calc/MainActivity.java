package com.my.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textResult; // （文本）显示内容
    private String firstNum = ""; // 数字一
    private String operator = ""; // 运算符
    private String secondNum = ""; // 数字二
    private String result = ""; // 计算结果
    private String showResult = ""; // 显示内容
    private int dot = 0;
    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.result);

        findViewById(R.id.bt_clear).setOnClickListener(this);
        findViewById(R.id.bt_square).setOnClickListener(this);
        findViewById(R.id.bt_sqrt).setOnClickListener(this);
        findViewById(R.id.bt_multiply).setOnClickListener(this);
        findViewById(R.id.bt_seven).setOnClickListener(this);
        findViewById(R.id.bt_eight).setOnClickListener(this);
        findViewById(R.id.bt_nine).setOnClickListener(this);
        findViewById(R.id.bt_divide).setOnClickListener(this);
        findViewById(R.id.bt_four).setOnClickListener(this);
        findViewById(R.id.bt_five).setOnClickListener(this);
        findViewById(R.id.bt_six).setOnClickListener(this);
        findViewById(R.id.bt_minus).setOnClickListener(this);
        findViewById(R.id.bt_one).setOnClickListener(this);
        findViewById(R.id.bt_two).setOnClickListener(this);
        findViewById(R.id.bt_three).setOnClickListener(this);
        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_reciprocal).setOnClickListener(this);
        findViewById(R.id.bt_zero).setOnClickListener(this);
        findViewById(R.id.bt_dot).setOnClickListener(this);
        findViewById(R.id.bt_equal).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String inputText = ((TextView) view).getText().toString();
        if (view.getId() == R.id.bt_clear) {
            clear();
        } else if (view.getId() == R.id.bt_add) {
            operator = inputText;
            refreshText(showResult + operator);
        } else if (view.getId() == R.id.bt_minus) {
            operator = inputText;
            refreshText(showResult + operator);
        } else if (view.getId() == R.id.bt_multiply) {
            operator = inputText;
            refreshText(showResult + operator);
        } else if (view.getId() == R.id.bt_divide) {
            operator = inputText;
            refreshText(showResult + operator);
        } else if (view.getId() == R.id.bt_equal) {
            double calcResult = calc();
            refreshOperate(String.valueOf(calcResult));
            refreshText(showResult + "=" + result);
        } else if (view.getId() == R.id.bt_sqrt) {
            double num = Double.parseDouble(firstNum);
            if (num >= 0) {
                double sqrtResult = Math.sqrt(num);
                refreshOperate(String.valueOf(sqrtResult));
                refreshText(showResult + "^1/2 = " + result);
            } else {
                refreshOperate("0");
                refreshText("Negative numbers do not have square roots!");
            }
        } else if (view.getId() == R.id.bt_square) {
            double squareResult = Math.pow(Double.parseDouble(firstNum), 2);
            refreshOperate(String.valueOf(squareResult));
            refreshText(showResult + "^2 = " + result);
        } else if (view.getId() == R.id.bt_reciprocal) {
            double num = Double.parseDouble(firstNum);
            if (num != 0) {
                double reciprocalResult = 1.0 / num;
                refreshOperate(String.valueOf(reciprocalResult));
                refreshText(showResult + "^-1 = " + result);
            } else {
                refreshOperate("0");
                refreshText("Divided by 0!");
            }
        } else {
            if (result.length() > 0 && !operator.equals("")) {
                clear();
            }
            if (operator.equals("")) {
                firstNum = firstNum + inputText;
            } else {
                secondNum = secondNum + inputText;
            }
            if (inputText.equals(".")) {
                if (dot == 0) {
                    refreshText(showResult + inputText);
                    dot = 1;
                }
            }
            if (showResult.equals("0") && !inputText.equals(".")) {
                refreshText(inputText);
            } else {
                refreshText(showResult + inputText);
            }

        }

    }

    private double calc() {
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            default:
                if (Double.parseDouble(secondNum) != 0) {
                    return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
                } else {
                    refreshText("Divided by 0!");
                    return Double.NaN;
                }
        }
    }// 返回计算结果

    private void clear() {
        refreshOperate("");
        refreshText("");
    } // 清空显示内容 操作数 操作符

    private void refreshOperate(String newResult) {
        result = newResult;
        firstNum = result;
        result = "";
        secondNum = "";
        operator = "";
    } // 刷新操作数与操作符 并把运算结果给result

    private void refreshText(String text) {
        showResult = text;
        textResult.setText(showResult);
    } // 刷新显示内容

}