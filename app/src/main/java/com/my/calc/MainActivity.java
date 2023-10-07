package com.my.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;

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
        } else if (view.getId() == R.id.bt_add || view.getId() == R.id.bt_minus || view.getId() == R.id.bt_multiply || view.getId() == R.id.bt_divide) {
            if (operator.equals("")) {
                operator = inputText;
                dot = 0;
                refreshText(showResult + operator);
            }
        } else if (view.getId() == R.id.bt_equal) {
            if (!operator.equals("") && !secondNum.equals("")) {
                refreshOperate(calc().toString());
                refreshText(showResult + "=" + result);
                result = "";
            }
        } else if (view.getId() == R.id.bt_sqrt) {
            double num = Double.parseDouble(firstNum);
            if (num >= 0) {
                double sqrtResult = Math.sqrt(num);
                BigDecimal bd = new BigDecimal(sqrtResult).setScale(6, BigDecimal.ROUND_HALF_UP);
                refreshOperate(bd.toString());
                refreshText(showResult + "^1/2 = " + result);
            } else {
                refreshOperate("0");
                refreshText("Negative numbers do not have square roots!");
            }
        } else if (view.getId() == R.id.bt_square) {
            BigDecimal squareResult = new BigDecimal(firstNum).pow(2).setScale(6, BigDecimal.ROUND_HALF_UP);
            refreshOperate(squareResult.toString());
            refreshText(showResult + "^2 = " + result);
        } else if (view.getId() == R.id.bt_reciprocal) {
            double num = Double.parseDouble(firstNum);
            if (num != 0) {
                BigDecimal reciprocalResult = BigDecimal.ONE.divide(new BigDecimal(firstNum), 6, BigDecimal.ROUND_HALF_UP);
                refreshOperate(reciprocalResult.toString());
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
            if (showResult.equals("0") && !inputText.equals(".")) {
                refreshText(inputText);
            } else {
                if (inputText.equals(".") && dot == 0) {
                    refreshText(showResult + inputText);
                    dot = 1;
                } else if (!inputText.equals(".")) {
                    refreshText(showResult + inputText);
                }
            }
        }
    }


    private BigDecimal calc() {
        BigDecimal f, s;
        f = new BigDecimal(firstNum);
        s = new BigDecimal(secondNum);
        switch (operator) {
            case "+":
                return f.add(s);
            case "-":
                return f.subtract(s);
            case "×":
                return f.multiply(s);
            default:
                if (!s.equals(BigDecimal.ZERO)) {
                    return f.divide(s, 6, BigDecimal.ROUND_HALF_UP);
                } else {
                    refreshText("Divided by 0!");
                    return BigDecimal.ZERO;
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
        secondNum = "";
        operator = "";
    } // 刷新操作数与操作符 并把运算结果给result

    private void refreshText(String text) {
        showResult = text;
        textResult.setText(showResult);
    } // 刷新显示内容

}