# QPageView

类似Android ViewPager，在滑动时有界面之间的分割线的效果。

##### 运行效果图
![运行效果图](https://github.com/hqs5678/hqs-common-pageview-android/blob/master/2017-08-16%2016_15_53.gif)

### 实现原理
封装RecyclerView，自定义PagerSnapHelper，添加分割线效果。

### 安装说明
### Gradle
```
    compile 'com.hqs.common.view.qpageview:qpageview:1.0.0'
```

 
#### 使用方法
与使用一般的RecyclerView类似，自定义Adapter，为每页设置您自己的View。

核心代码如下：

```
pageView = (QPageView) findViewById(R.id.pageView);

// 设置分割线的颜色
pageView.setSeparatorColor(Color.rgb(15, 9, 7));
// 设置分割线的宽度
pageView.setSeparatorWidth(DensityUtils.dp2px(this, 22));

// 设置adapter
pageView.setAdapter(new RecyclerView.Adapter<TestViewHolder>() {
    @Override
    public TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_test, null);

        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TestViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
});

```


如有其他问题， 请下载运行项目，参考示例代码。
