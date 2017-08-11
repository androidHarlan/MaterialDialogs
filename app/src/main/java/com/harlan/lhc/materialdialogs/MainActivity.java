package com.harlan.lhc.materialdialogs;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.materialDialog)
    Button materialDialog;
    @Bind(R.id.DisplayinganIcon)
    Button DisplayinganIcon;
    @Bind(R.id.Stackingbehavior)
    Button Stackingbehavior;
    @Bind(R.id.NeutralActionButton)
    Button NeutralActionButton;
    @Bind(R.id.Callbacks)
    Button Callbacks;
    @Bind(R.id.CheckBoxPrompts)
    Button CheckBoxPrompts;
    @Bind(R.id.ListDialogs)
    Button ListDialogs;
    @Bind(R.id.SingleChoiceListDialogs)
    Button SingleChoiceListDialogs;
    @Bind(R.id.MultiChoiceListDialogs)
    Button MultiChoiceListDialogs;
    @Bind(R.id.AssigningIDstoListItemViews)
    Button AssigningIDstoListItemViews;
    @Bind(R.id.CustomListDialogs)
    Button CustomListDialogs;
    @Bind(R.id.CustomViews)
    Button CustomViews;
    @Bind(R.id.Colors)
    Button Colors;
    @Bind(R.id.Gravity)
    Button Gravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Gravity,R.id.Colors, R.id.CustomViews, R.id.CustomListDialogs, R.id.AssigningIDstoListItemViews, R.id.MultiChoiceListDialogs, R.id.SingleChoiceListDialogs, R.id.ListDialogs, R.id.CheckBoxPrompts, R.id.Callbacks, R.id.NeutralActionButton, R.id.Stackingbehavior, R.id.materialDialog, R.id.DisplayinganIcon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ListDialogs:
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .items(R.array.items)

                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            }
                        })
                        .show();
                break;
            case R.id.Gravity:
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .neutralText(R.string.more_info)
                        .titleGravity(GravityEnum.CENTER)
                        .contentGravity(GravityEnum.CENTER)
                        .btnStackedGravity(GravityEnum.START)
                        .itemsGravity(GravityEnum.END)
                        .buttonsGravity(GravityEnum.END)
                        .show();
                break;
            case R.id.Colors:
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .neutralText(R.string.more_info)
                        .titleColorRes(R.color.material_red_400)
                        .contentColor(Color.WHITE) // notice no 'res' postfix for literal color
                                //.linkColorAttr(R.attr.my_link_color_attr)  // notice attr is used instead of none or res for attribute resolving
                        .dividerColorRes(R.color.primaryDark)
                        .backgroundColorRes(R.color.material_blue_grey_800)
                        .positiveColorRes(R.color.material_red_400)
                        .neutralColorRes(R.color.material_red_400)
                        .negativeColorRes(R.color.material_red_400)
                        .widgetColorRes(R.color.material_red_400)
                        .buttonRippleColorRes(R.color.material_red_400)
                        .show();
                break;
            case R.id.CustomViews:
                boolean wrapInScrollView = true;
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .customView(R.layout.custom_view, wrapInScrollView)
                        .positiveText(R.string.agree)
                        .show();
                break;
            case R.id.CustomListDialogs:
                new MaterialDialog.Builder(this)
                        .title(R.string.socialNetworks)
                                // second parameter is an optional layout manager. Must be a LinearLayoutManager or GridLayoutManager.
                        .adapter(new ButtonItemAdapter(this, R.array.socialNetworks), null)
                        .show();
                break;
            case R.id.AssigningIDstoListItemViews:
                new MaterialDialog.Builder(this)
                        .title(R.string.socialNetworks)
                        .items(R.array.socialNetworks)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Toast.makeText(MainActivity.this, which + ": " + text + ", ID = " + view.getId(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.MultiChoiceListDialogs:
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .items(R.array.items)
                        .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                                /**
                                 * If you use alwaysCallMultiChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected check box to actually be selected
                                 * (or the newly unselected check box to be unchecked).
                                 * See the limited multi choice dialog example in the sample project for details.
                                 **/
                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .show();
                break;
            case R.id.SingleChoiceListDialogs:
                Resources resource = (Resources) getBaseContext().getResources();
                ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.button_text);
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .items(R.array.items)
                                // .choiceWidgetColor(csl)//设置选择框的样式
                        .itemsColor(Color.parseColor("#ff00ff"))

                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                return true;
                            }
                        })
                        .positiveText(R.string.choose)
                        .show();
                break;
            case R.id.Callbacks:
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .neutralText(R.string.more_info)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        })
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                // TODO
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                // TODO
                            }
                        })
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                // TODO
                            }
                        }).show();
                break;
            case R.id.Stackingbehavior:
                new MaterialDialog.Builder(this)
                        .content(R.string.content)
                        .stackingBehavior(StackingBehavior.ADAPTIVE)  // the default value
                        .show();
                 /* MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.agree);

                MaterialDialog dialog = builder.build();
                dialog.show();*/
                break;
            case R.id.NeutralActionButton:
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .neutralText(R.string.more_info)
                        .show();
                break;
            case R.id.CheckBoxPrompts:
                new MaterialDialog.Builder(this)
                        .iconRes(R.drawable.ic_launcher)
                        .limitIconToDefaultSize()
                        .title(R.string.title)
                        .positiveText(R.string.allow)
                        .negativeText(R.string.deny)
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        })
                        .checkBoxPromptRes(R.string.dont_ask_again, false, null)
                        .show();
                break;
            case R.id.materialDialog:
                new MaterialDialog.Builder(this)
                        .title("这是标题")

                        .content("这是内容")
                        .positiveText("同意")
                        .negativeText("不同意")
                        .show();
                break;
            case R.id.DisplayinganIcon:
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.agree)
                        .iconRes(R.mipmap.ic_launcher)
                        .show();
                break;
        }
    }
}
