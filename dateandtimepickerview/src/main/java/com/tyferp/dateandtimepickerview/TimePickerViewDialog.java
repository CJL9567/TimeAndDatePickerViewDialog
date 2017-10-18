package com.tyferp.dateandtimepickerview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/10/17.
 */

public class TimePickerViewDialog extends Dialog implements NumberPickerView.OnValueChangeListener{
    private static final String TAG = "picker";
    private TimePickerViewCallBack timePickerViewCallBack;
    private Context mContext;
    public NumberPickerView mPickerViewH;
    private NumberPickerView mPickerViewM;
    private TextView submitText,cancelText;
    private String [] values;
    public TimePickerViewDialog(@NonNull Context context,String [] values) {
        super(context);
        mContext = context;
        this.values=values;
    }
    public TimePickerViewDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public void setTimePickerViewCallBack(TimePickerViewCallBack timePickerViewCallBack) {
        this.timePickerViewCallBack = timePickerViewCallBack;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_pickerview_dialog);
        Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month;
        int date = t.monthDay;
        int hour = t.hour;
        int min=t.minute;
        mPickerViewH = (NumberPickerView)this.findViewById(R.id.picker_hour);
        mPickerViewH.setMaxValue(23);
//        mPickerViewH.setDisplayedValues(values);
        mPickerViewH.setValue(hour);
        mPickerViewM = (NumberPickerView)this.findViewById(R.id.picker_minute);
        mPickerViewM.setMaxValue(59);
        mPickerViewM.setValue(min);
        mPickerViewH.setOnValueChangedListener(this);
        mPickerViewM.setOnValueChangedListener(this);
        submitText=(TextView) findViewById(R.id.submitText);
        cancelText=(TextView)findViewById(R.id.cancelText);
        submitText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String h = mPickerViewH.getContentByCurrValue();
                String m = mPickerViewM.getContentByCurrValue();
                timePickerViewCallBack.timePickerViewCallBack(h + getContext().getString(R.string.hour_hint) + " "
                        + m + getContext().getString(R.string.minute_hint));
            }
        });
        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerViewCallBack.timePickerViewCallBack("");
            }
        });
    }

    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {

    }


    //点击确定时的回调函数，返回选择的时间
    public interface TimePickerViewCallBack{
        public void timePickerViewCallBack(String time);
    }

}
