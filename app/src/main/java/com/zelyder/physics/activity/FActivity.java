package com.zelyder.physics.activity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.zelyder.physics.Fragments.DoubleFractionView;
import com.zelyder.physics.Fragments.FractionView;
import com.zelyder.physics.Fragments.KeyboardConstantCurrent;
import com.zelyder.physics.Fragments.KeyboardDynamics;
import com.zelyder.physics.Fragments.KeyboardElectromagneticOscillations;
import com.zelyder.physics.Fragments.KeyboardElectrostatics;
import com.zelyder.physics.Fragments.KeyboardEnergy;
import com.zelyder.physics.Fragments.KeyboardHydrostatics;
import com.zelyder.physics.Fragments.KeyboardKinematicsFragment;
import com.zelyder.physics.Fragments.KeyboardMagneticField;
import com.zelyder.physics.Fragments.KeyboardMechanicalVibrations;
import com.zelyder.physics.Fragments.KeyboardMolecularPhysics;
import com.zelyder.physics.Fragments.KeyboardOptics;
import com.zelyder.physics.Fragments.KeyboardQuantumPhysics;
import com.zelyder.physics.Fragments.KeyboardSTO;
import com.zelyder.physics.Fragments.KeyboardStatics;
import com.zelyder.physics.Fragments.KeyboardThermodynamics;
import com.zelyder.physics.Fragments.STOFractionView;
import com.zelyder.physics.PhysicsApp;
import com.zelyder.physics.help.Utilits;
import com.zelyder.physics.model.DelFormula;
import com.zelyder.physics.model.Favorite;
import com.zelyder.physics.model.Formula;
import com.zelyder.user.physics.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class FActivity extends AppCompatActivity {
    public static final String TITLE = "title";
    public static final String BOOL = "bool";
    public static final String TAG = "FActivity";
    public ArrayList<Formula> listFormulas;
    public ArrayList<Favorite> listFavorite;
    public ArrayList<DelFormula> listDelFormulas;
    private Iterator<DataSnapshot> iterable;
    private Realm mRealm;
    private Realm mRealmForDel;

    private FractionView fractionView;
    private DoubleFractionView doubleFractionView;
    private STOFractionView stoFractionView;
    private Utilits utilits;
    private DatabaseReference myRef;
    private TextView tvTitle;
    private LikeButton starButton;
    private int formulaId;
    private int correctAnswers;
    private int countFormulas;
    private int countTry, startTry;
    private boolean isFavorite;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (preferences.getBoolean("cb_pref_dark_style", false)) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f);
        utilits = new Utilits();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.keepSynced(true);
        mRealm = Realm.getInstance(PhysicsApp.getConfigForFavorites());
        mRealmForDel = Realm.getInstance(PhysicsApp.getConfigForDelFormulas());

        listFormulas = new ArrayList<>();
        listFavorite = new ArrayList<>();
        listDelFormulas = new ArrayList<>();

        tvTitle = findViewById(R.id.tvTitle);
        fractionView = findViewById(R.id.fractionView);
        doubleFractionView = findViewById(R.id.doubleFractionView);
        stoFractionView = findViewById(R.id.STOFractionView);
        starButton = findViewById(R.id.test_star_button);

        startTry = Integer.parseInt(preferences.getString("pref_list_count_try","3"));

        isFavorite = getIntent().getBooleanExtra(BOOL, false);

        if (isFavorite) {
            setUpFavorite();
        } else {
            setUpDB();
        }


        starButton.setOnLikeListener(new OnLikeListener() {


            @Override
            public void liked(LikeButton likeButton) {
                if (!listFormulas.isEmpty()) {
                    Formula item = listFormulas.get(formulaId);
                    mRealm.beginTransaction();
                    Favorite favorite = mRealm.createObject(Favorite.class);
                    favorite.setSection(getIntent().getStringExtra(TITLE));
                    favorite.setName(item.getTitle());
                    favorite.setFormula(item.getFormula());
                    mRealm.commitTransaction();
                } else {
                    onBackPressed();
                }
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Formula item = listFormulas.get(formulaId);

                mRealm.beginTransaction();
                RealmResults<Favorite> favorites = mRealm.where(Favorite.class)
                        .equalTo("name", item.getTitle()).findAll();
                for (int i = favorites.size() - 1; i >= 0; i--) {
                    if (favorites.get(i) != null) {
                        favorites.get(i).deleteFromRealm();
                    }
                }
                mRealm.commitTransaction();
                if (isFavorite) {
                    nextFormula();
                }
            }
        });

        if(!isFavorite){
            setUpKeyboard();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mRealm.isEmpty()) {
            mRealm.close();
        }
    }

    private void getRandomFormula() {
        Random random = new Random();
        formulaId = random.nextInt(listFormulas.size());
        Formula formula = listFormulas.get(formulaId);
        tvTitle.setText(formula.getTitle());
        Log.d("LOL", utilits.toUnknown(formula.getFormula()));
        if (isFavorite) {
            replaceKeyboard();
        }
        checkFavor();

        if (formula.getFormula().split("[=]")[0].contains("/")) {
            doubleFractionView.setVisibility(View.VISIBLE);
            fractionView.setVisibility(View.GONE);
            stoFractionView.setVisibility(View.GONE);
            doubleFractionView.setDoubleFraction(utilits.toUnknown(formula.getFormula()));
        } else if (formula.getFormula().split("[=]")[1].contains("◤")
                || formula.getFormula().split("[=]")[1].contains("◢")
                || formula.getFormula().split("[=]")[1].contains("◇")) {
            stoFractionView.setVisibility(View.VISIBLE);
            doubleFractionView.setVisibility(View.GONE);
            fractionView.setVisibility(View.GONE);
            stoFractionView.setSTOFraction(utilits.toUnknown(formula.getFormula()));
        } else {
            fractionView.setVisibility(View.VISIBLE);
            doubleFractionView.setVisibility(View.GONE);
            stoFractionView.setVisibility(View.GONE);
            fractionView.setFraction(utilits.toUnknown(formula.getFormula()));
        }
        countTry = startTry;
    }

    private void setUpDB() {
        setUpDelFormulas();
        myRef.child(getIntent().getStringExtra(TITLE)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                iterable = dataSnapshot.getChildren().iterator();
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    DataSnapshot dataFormula = iterable.next();
                    Formula formula = new Formula(dataFormula.getKey(), String.valueOf(dataFormula
                            .getValue()));
                    if(!isDelete(formula.getTitle())){
                        listFormulas.add(formula);
                    }
                }
                countFormulas = listFormulas.size();
                if(countFormulas != 0){
                    getRandomFormula();
                }else {
                    Toast.makeText(FActivity.this,R.string.noElements,Toast.LENGTH_LONG).show();
                    onBackPressed();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

    private void setUpFavorite() {
        RealmResults<Favorite> favorites = mRealm.where(Favorite.class).findAll();
        for (int i = 0; i < favorites.size(); i++) {
            if (favorites.get(i) != null) {
                listFavorite.add(favorites.get(i));
                Formula formula = new Formula(favorites.get(i).getName(), favorites.get(i).getFormula());
                listFormulas.add(formula);
            }
        }
        countFormulas = listFormulas.size();
        getRandomFormula();
    }

    private void setUpDelFormulas(){
        RealmResults<DelFormula> delFormulas = mRealmForDel.where(DelFormula.class).findAll();
        for (int i = 0; i < delFormulas.size(); i++) {
            if (delFormulas.get(i) != null) {
                listDelFormulas.add(delFormulas.get(i));
            }
        }
    }

    private boolean isDelete(String nameFormula){
        for(int i = 0;i < listDelFormulas.size();i++){
            if(listDelFormulas.get(i).getName().equals(nameFormula)){
                return true;
            }
        }
        return false;
    }

    public void insertChar(String ch) {
        if (doubleFractionView.getVisibility() == View.VISIBLE) {
            doubleFractionView.setDoubleFraction(utilits.addChar(doubleFractionView.getDoubleFraction(), ch));
        } else if (stoFractionView.getVisibility() == View.VISIBLE) {
            stoFractionView.setSTOFraction(utilits.addChar(stoFractionView.getSTOFraction(), ch));
        } else if (fractionView.getVisibility() == View.VISIBLE) {
            fractionView.setFraction(utilits.addChar(fractionView.getFraction(), ch));
        }
    }

    public void delCh() {
        if (doubleFractionView.getVisibility() == View.VISIBLE) {
            doubleFractionView.setDoubleFraction(utilits.delChar(doubleFractionView.getDoubleFraction()));
        } else if (stoFractionView.getVisibility() == View.VISIBLE) {
            stoFractionView.setSTOFraction(utilits.delChar(stoFractionView.getSTOFraction()));
        } else if (fractionView.getVisibility() == View.VISIBLE) {
            fractionView.setFraction(utilits.delChar(fractionView.getFraction()));
        }
    }

    public void clickBtnOk() {
        if (listFormulas.size() != 0) {
            String checkableStr = "";
            if (fractionView.getVisibility() == View.VISIBLE) {
                checkableStr = fractionView.getFraction();
            } else if (doubleFractionView.getVisibility() == View.VISIBLE) {
                checkableStr = doubleFractionView.getDoubleFraction();
            } else if (stoFractionView.getVisibility() == View.VISIBLE) {
                checkableStr = stoFractionView.getSTOFraction();
            }
            if (utilits.equals(checkableStr, listFormulas.get(formulaId).getFormula())) {
                if (countTry > 0) {
                    if (preferences.getBoolean("pref_sw_consider", false)) {
                        correctAnswers += countTry * 5;
                    } else {
                        correctAnswers++;
                    }
                }
                nextFormula();
            } else {
                countTry--;
                if (countTry > 0) {
                    Toast.makeText(this, "Неправильно осталось попыток: " + countTry,
                            Toast.LENGTH_SHORT).show();
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(FActivity.this);
                    final LayoutInflater inflater = this.getLayoutInflater();
                    final View view = inflater.inflate(R.layout.dialog_correct_formula, null);

                    FractionView formula = view.findViewById(R.id.dialog_formula);
                    DoubleFractionView formulaV2 = view.findViewById(R.id.dialog_doubleFormula);
                    STOFractionView formulaV3 = view.findViewById(R.id.dialog_STOFormula);

                    if (listFormulas.get(formulaId).getFormula().split("[=]")[0].contains("/")) {
                        formulaV2.setDoubleFraction(listFormulas.get(formulaId).getFormula());
                    } else if (listFormulas.get(formulaId).getFormula().split("[=]")[1].contains("◤")
                            || listFormulas.get(formulaId).getFormula().split("[=]")[1].contains("◢")
                            || listFormulas.get(formulaId).getFormula().split("[=]")[1].contains("◇")) {
                        formulaV3.setVisibility(View.VISIBLE);
                        formulaV3.setSTOFraction(listFormulas.get(formulaId).getFormula());
                    } else {
                        formula.setFraction(listFormulas.get(formulaId).getFormula());
                    }

                    builder.setView(view)
                            .setTitle("Правильная формула")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    nextFormula();
                                }
                            })
                            .create().show();
                }
            }
        } else {
            nextFormula();
        }
    }

    public void LongClickBtnOk() {
        countTry = 0;
        clickBtnOk();
    }


    private void nextFormula() {
        if (listFormulas.size() != 0) {
            listFormulas.remove(formulaId);
            if (isFavorite) {
                listFavorite.remove(formulaId);
            }
        }
        if (listFormulas.size() == 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final LayoutInflater inflater = this.getLayoutInflater();
            final View view = inflater.inflate(R.layout.dialog_end_test, null);

            PieChart pieChart = view.findViewById(R.id.pieChart);
            ArrayList<PieEntry> entries = new ArrayList<>();
            if (correctAnswers != 0) {
                entries.add(new PieEntry(correctAnswers, "Правильно"));
            }
            if (preferences.getBoolean("pref_sw_consider", false)) {
                if ((countFormulas * (startTry*5)) - correctAnswers != 0) {
                    entries.add(new PieEntry((countFormulas * (startTry*5)) - correctAnswers
                            , "Неправильно"));
                }
            }else {
                if (countFormulas - correctAnswers != 0) {
                    entries.add(new PieEntry(countFormulas - correctAnswers, "Неправильно"));
                }
            }

            PieDataSet pieDataSet = new PieDataSet(entries, "");
            pieDataSet.setSliceSpace(2f);
            pieDataSet.setValueTextSize(10f);
            pieDataSet.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return String.valueOf((int)value);
                }
            });
            PieData pieData = new PieData(pieDataSet);
            if (correctAnswers == 0) {
                pieDataSet.setColors(getResources().getColor(R.color.colorWrong));
            } else if ((countFormulas - correctAnswers == 0)
                    || ((preferences.getBoolean("pref_sw_consider", false))
                    && ((countFormulas*(startTry*5)) - correctAnswers) == 0)) {
                pieDataSet.setColors(getResources().getColor(R.color.colorCorrect));
            } else {
                pieDataSet.setColors(getResources().getColor(R.color.colorCorrect)
                        , getResources().getColor(R.color.colorWrong));
            }
            pieChart.setDrawHoleEnabled(false);
            Description dis = new Description();
            dis.setText("");
            pieChart.setDescription(dis);
            pieChart.setEntryLabelColor((getResources().getColor(R.color.colorPieText)));
            pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);
            pieChart.setData(pieData);
            pieChart.invalidate();


            builder.setTitle("Вы прошли тест");
            builder.setView(view);
            builder.setPositiveButton("Выход", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();
                }
            }).create().show();

        } else {
            getRandomFormula();
        }
    }

    private void setUpKeyboard() {
        FragmentManager fragmentManager = getFragmentManager();
        String section;
        if (isFavorite) {
            section = listFavorite.get(formulaId).getSection();
        } else {
            section = getIntent().getStringExtra(TITLE);
        }

        setTitle(section);

        switch (section) {
            case "Кинематика":
                KeyboardKinematicsFragment keyboardKinematics = new KeyboardKinematicsFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardKinematics).commit();
                break;
            case "Динамика":
                KeyboardDynamics keyboardDynamics = new KeyboardDynamics();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardDynamics).commit();
                break;
            case "Статика":
                KeyboardStatics keyboardStatics = new KeyboardStatics();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardStatics).commit();
                break;
            case "Гидростатика":
                KeyboardHydrostatics keyboardHydrostatics = new KeyboardHydrostatics();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardHydrostatics).commit();
                break;
            case "Работа, мощность, энергия":
                KeyboardEnergy keyboardEnergy = new KeyboardEnergy();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardEnergy).commit();
                break;
            case "Молекулярная физика":
                KeyboardMolecularPhysics keyboardMolecularPhysics = new KeyboardMolecularPhysics();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardMolecularPhysics).commit();
                break;
            case "Термодинамика":
                KeyboardThermodynamics keyboardThermodynamics = new KeyboardThermodynamics();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardThermodynamics).commit();
                break;
            case "Электростатика":
                KeyboardElectrostatics keyboardElectrostatics = new KeyboardElectrostatics();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardElectrostatics).commit();
                break;
            case "Законы постоянного тока":
                KeyboardConstantCurrent keyboardConstantCurrent = new KeyboardConstantCurrent();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardConstantCurrent).commit();
                break;
            case "Магнитное поле, электромагнитная индукция":
                KeyboardMagneticField keyboardMagneticField = new KeyboardMagneticField();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardMagneticField).commit();
                break;
            case "Механические колебания и волны":
                KeyboardMechanicalVibrations keyboardMechanicalVibrations =
                        new KeyboardMechanicalVibrations();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardMechanicalVibrations).commit();
                break;
            case "Электромагнитные колебания и волны":
                KeyboardElectromagneticOscillations keyboardElectromagneticOscillations =
                        new KeyboardElectromagneticOscillations();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardElectromagneticOscillations)
                        .commit();
                break;
            case "Оптика":
                KeyboardOptics keyboardOptics = new KeyboardOptics();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardOptics)
                        .commit();
                break;
            case "Специальная теория относительности (СТО)":
                KeyboardSTO keyboardSTO = new KeyboardSTO();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardSTO)
                        .commit();
                break;
            case "Квантовая физика":
                KeyboardQuantumPhysics keyboardQuantumPhysics = new KeyboardQuantumPhysics();
                fragmentManager.beginTransaction()
                        .add(R.id.container_for_fragments, keyboardQuantumPhysics)
                        .commit();
                break;
        }
    }

    private void replaceKeyboard() {
        FragmentManager fragmentManager = getFragmentManager();
        setTitle(listFavorite.get(formulaId).getSection());
        switch (listFavorite.get(formulaId).getSection()) {
            case "Кинематика":
                KeyboardKinematicsFragment keyboardKinematics = new KeyboardKinematicsFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardKinematics).commit();
                break;
            case "Динамика":
                KeyboardDynamics keyboardDynamics = new KeyboardDynamics();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardDynamics).commit();
                break;
            case "Статика":
                KeyboardStatics keyboardStatics = new KeyboardStatics();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardStatics).commit();
                break;
            case "Гидростатика":
                KeyboardHydrostatics keyboardHydrostatics = new KeyboardHydrostatics();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardHydrostatics).commit();
                break;
            case "Работа, мощность, энергия":
                KeyboardEnergy keyboardEnergy = new KeyboardEnergy();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardEnergy).commit();
                break;
            case "Молекулярная физика":
                KeyboardMolecularPhysics keyboardMolecularPhysics = new KeyboardMolecularPhysics();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardMolecularPhysics).commit();
                break;
            case "Термодинамика":
                KeyboardThermodynamics keyboardThermodynamics = new KeyboardThermodynamics();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardThermodynamics).commit();
                break;
            case "Электростатика":
                KeyboardElectrostatics keyboardElectrostatics = new KeyboardElectrostatics();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardElectrostatics).commit();
                break;
            case "Законы постоянного тока":
                KeyboardConstantCurrent keyboardConstantCurrent = new KeyboardConstantCurrent();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardConstantCurrent).commit();
                break;
            case "Магнитное поле, электромагнитная индукция":
                KeyboardMagneticField keyboardMagneticField = new KeyboardMagneticField();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardMagneticField).commit();
                break;
            case "Механические колебания и волны":
                KeyboardMechanicalVibrations keyboardMechanicalVibrations =
                        new KeyboardMechanicalVibrations();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardMechanicalVibrations).commit();
                break;
            case "Электромагнитные колебания и волны":
                KeyboardElectromagneticOscillations keyboardElectromagneticOscillations =
                        new KeyboardElectromagneticOscillations();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardElectromagneticOscillations)
                        .commit();
                break;
            case "Оптика":
                KeyboardOptics keyboardOptics = new KeyboardOptics();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardOptics)
                        .commit();
                break;
            case "Специальная теория относительности (СТО)":
                KeyboardSTO keyboardSTO = new KeyboardSTO();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardSTO)
                        .commit();
                break;
            case "Квантовая физика":
                KeyboardQuantumPhysics keyboardQuantumPhysics = new KeyboardQuantumPhysics();
                fragmentManager.beginTransaction()
                        .replace(R.id.container_for_fragments, keyboardQuantumPhysics)
                        .commit();
                break;
        }
    }

    private void checkFavor() {
        if (isFavorite) {
            starButton.setLiked(true);
        } else {

            Favorite favorite = mRealm.where(Favorite.class)
                    .equalTo("name",
                            listFormulas.get(formulaId).getTitle())
                    .findFirst();

            if (favorite != null) {
                starButton.setLiked(true);
            } else {
                starButton.setLiked(false);
            }
        }
    }
}
