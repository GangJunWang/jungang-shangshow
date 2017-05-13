package com.shangshow.showlive.controller.personal.pay;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shangshow.showlive.R;
import com.shangshow.showlive.base.BaseActivity;
import com.shangshow.showlive.common.widget.custom.BaseButton;
import com.shangshow.showlive.network.service.models.responseBody.BuyProductResponse;

/**
 * 支付订单界面
 */
public class PayOrderActivity extends BaseActivity implements View.OnClickListener {

    private View ll_pay_order_zhifubao;
    private View ll_pay_order_weixin;
    TextView tv_pay_order_acount;
    TextView tv_pay_order_sum;
    ImageView iv_pay_order_zhifubao;
    ImageView iv_pay_order_weixin;
    private BaseButton bb_pay_order_pay;
    private int check = 1;
    private BuyProductResponse buyProductResponse;
    public static final String PAYORDER_BUYPRODUCT = "payorder_buyproduct";

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_pay_order;
    }

    @Override
    protected void setContentViewOption(int resId) {
        super.setContentViewOption(resId);
        buyProductResponse = (BuyProductResponse) getIntent().getSerializableExtra(PAYORDER_BUYPRODUCT);
    }

    @Override
    protected void bindEven() {
        ll_pay_order_zhifubao.setOnClickListener(this);
        ll_pay_order_weixin.setOnClickListener(this);
        bb_pay_order_pay.setOnClickListener(this);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        titleBarView.initCenterTitle(getString(R.string.person_pay_order));
        ll_pay_order_zhifubao = findViewById(R.id.ll_pay_order_zhifubao);
        ll_pay_order_weixin = findViewById(R.id.ll_pay_order_weixin);
        tv_pay_order_acount = (TextView) findViewById(R.id.tv_pay_order_acount);
        tv_pay_order_sum = (TextView) findViewById(R.id.tv_pay_order_sum);
        iv_pay_order_zhifubao = (ImageView) findViewById(R.id.iv_pay_order_zhifubao);
        iv_pay_order_weixin = (ImageView) findViewById(R.id.iv_pay_order_weixin);
        bb_pay_order_pay = (BaseButton) findViewById(R.id.bb_pay_order_pay);
        String orderNo = getIntent().getStringExtra("orderNo");
        String amount = getIntent().getStringExtra("jiushiwo");
        tv_pay_order_sum.setText("￥" + amount + "");
        tv_pay_order_acount.setText(orderNo);

    }

    @Override
    protected void setView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_pay_order_zhifubao:{
                iv_pay_order_zhifubao.setBackgroundResource(R.drawable.xuanzhong);
                iv_pay_order_weixin.setBackgroundResource(R.drawable.weixuanzhong);
                check = 1;
            }
            break;
            case R.id.ll_pay_order_weixin:{
                iv_pay_order_zhifubao.setBackgroundResource(R.drawable.weixuanzhong);
                iv_pay_order_weixin.setBackgroundResource(R.drawable.xuanzhong);
                check = 2;
            }
            break;
            case R.id.bb_pay_order_pay:{

            }
            break;
        }
    }
}
