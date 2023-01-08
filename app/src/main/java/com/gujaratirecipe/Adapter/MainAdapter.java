package com.gujaratirecipe.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gujaratirecipe.Activity.MainActivity;
import com.gujaratirecipe.Activity.SecondActivity;
import com.gujaratirecipe.Database;
import com.gujaratirecipe.Model.Model;
import com.gujaratirecipe.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    Activity activity;
    Database database;
    List<Model> models;
    public static List<Model> modelList;
    String[] name = new String[]{"રોટી/પરાઠા","સબ્જી","નાસ્તા","મીઠાઈ","ફરાળી વાનગી","આઈસ્ક્રીમ/શરબત","હેલ્ધી નાસ્તો","ફેસ્ટિવલ નાસ્તો","બાળકો નો નાસ્તો","રાયતા/સલાડ","ડાયાબિટીશ સ્પેશયલ"};
    int[] pic = new int[]{R.drawable.paratha,R.drawable.sabji,R.drawable.nasta,R.drawable.mithai,R.drawable.falali,R.drawable.icecremsarbat,R.drawable.healthybrak,R.drawable.festival,R.drawable.child,R.drawable.icon6,R.drawable.icon18};
    int[] B;

    int[] rotipic = new int[]{R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,R.drawable.d11,R.drawable.d12,R.drawable.d13,R.drawable.d14,R.drawable.d15,R.drawable.d16,R.drawable.d17,R.drawable.d18,R.drawable.d19,R.drawable.d2,R.drawable.d21,R.drawable.d22,R.drawable.d23,R.drawable.d24,R.drawable.d25,R.drawable.d26,R.drawable.d27,R.drawable.d28,R.drawable.d29,R.drawable.d30,R.drawable.d31,R.drawable.d32,R.drawable.d33,R.drawable.d34,R.drawable.d35,R.drawable.d36,R.drawable.d37,R.drawable.d38,R.drawable.d39,R.drawable.d40,R.drawable.d41,R.drawable.d42,R.drawable.d43};
    int[] sabjipic = new int[]{R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13,R.drawable.c14,R.drawable.c15,R.drawable.c16,R.drawable.c17,R.drawable.c18,R.drawable.c19,R.drawable.c20,R.drawable.c21,R.drawable.c22,R.drawable.c23,R.drawable.c24,R.drawable.c25,R.drawable.c26,R.drawable.c27,R.drawable.c28,R.drawable.c29,R.drawable.c30,R.drawable.c31,R.drawable.c32,R.drawable.c33,R.drawable.c34,R.drawable.c35,R.drawable.c36,R.drawable.c37,R.drawable.c38,R.drawable.c39,R.drawable.c40,R.drawable.c41,R.drawable.c42,R.drawable.c43,R.drawable.c44,R.drawable.c45,R.drawable.c46,R.drawable.c47,R.drawable.c48,R.drawable.c49,R.drawable.c50,R.drawable.c51,R.drawable.c52,R.drawable.c53,R.drawable.c54,R.drawable.c55,R.drawable.c56,R.drawable.c57,R.drawable.c58,R.drawable.c59,R.drawable.c60,R.drawable.c61,R.drawable.c62,R.drawable.c63,R.drawable.c64,R.drawable.c65,R.drawable.c66,R.drawable.c67,R.drawable.c68,R.drawable.c69,R.drawable.c70,R.drawable.c71,R.drawable.c72,R.drawable.c73,R.drawable.c74,R.drawable.c75,R.drawable.c76,R.drawable.c77,R.drawable.c78,R.drawable.c79,};
    int[] nastapic = new int[]{R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6,R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10,R.drawable.a11,R.drawable.a12,R.drawable.a13,R.drawable.a14,R.drawable.a15,R.drawable.a16,R.drawable.a17,R.drawable.a18,R.drawable.a19,R.drawable.a20,R.drawable.a21,R.drawable.a22,R.drawable.a23,R.drawable.a24,R.drawable.a25,R.drawable.a26,R.drawable.a27,R.drawable.a28,R.drawable.a29,R.drawable.a30,R.drawable.a31,R.drawable.a32,R.drawable.a33,R.drawable.a34,R.drawable.a35,R.drawable.a36,R.drawable.a37,R.drawable.a38,R.drawable.a39,R.drawable.a40,R.drawable.a41,R.drawable.a42,R.drawable.a43,R.drawable.a44,R.drawable.a45,R.drawable.a46,R.drawable.a47,R.drawable.a48,R.drawable.a49,R.drawable.a50,R.drawable.a51,R.drawable.a52,R.drawable.a53,R.drawable.a54,R.drawable.a55,R.drawable.a56,R.drawable.a57,R.drawable.a58,R.drawable.a59,R.drawable.a60,R.drawable.a61,R.drawable.a62,R.drawable.a63,R.drawable.a64,R.drawable.a65,R.drawable.a66,R.drawable.a67,R.drawable.a68,R.drawable.a69,R.drawable.a70,R.drawable.a71,R.drawable.a72,R.drawable.a73,R.drawable.a74,R.drawable.a75,R.drawable.a76,R.drawable.a77,R.drawable.a78,R.drawable.a79,R.drawable.a80,R.drawable.a81,R.drawable.a82,R.drawable.a83,R.drawable.a84,R.drawable.a85,R.drawable.a86,R.drawable.a87,R.drawable.a88,R.drawable.a89,R.drawable.a90,R.drawable.a91,R.drawable.a92,R.drawable.a93,R.drawable.a94,R.drawable.a95,R.drawable.a96,R.drawable.a97,R.drawable.a98,R.drawable.a99,R.drawable.a100,R.drawable.a101,R.drawable.a102,R.drawable.a103,R.drawable.a104,R.drawable.a105,R.drawable.a106,R.drawable.a107,R.drawable.a108,R.drawable.a109,R.drawable.a110,R.drawable.a111,R.drawable.a112,R.drawable.a113,R.drawable.a114,R.drawable.a115};
    int[] mithaipic = new int[]{R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6,R.drawable.b7,R.drawable.b8,R.drawable.b9,R.drawable.b10,R.drawable.b11,R.drawable.b12,R.drawable.b13,R.drawable.b14,R.drawable.b15,R.drawable.b16,R.drawable.b17,R.drawable.b18,R.drawable.b19,R.drawable.b20,R.drawable.b21,R.drawable.b22,R.drawable.b23,R.drawable.b24,R.drawable.b25,R.drawable.b26,R.drawable.b27,R.drawable.b28,R.drawable.b29,R.drawable.b30,R.drawable.b31,R.drawable.b32,R.drawable.b33,R.drawable.b34,R.drawable.b35,R.drawable.b36,R.drawable.b37,R.drawable.b38,R.drawable.b39,R.drawable.b40,R.drawable.b41,R.drawable.b42,R.drawable.b43,R.drawable.b44,R.drawable.b45,R.drawable.b46,R.drawable.b47,R.drawable.b48,R.drawable.b49,R.drawable.b50,R.drawable.b51,R.drawable.b52,R.drawable.b53,R.drawable.b54,R.drawable.b55,R.drawable.b56,R.drawable.b57,R.drawable.b58,R.drawable.b59,R.drawable.b60,R.drawable.b61,R.drawable.b62,R.drawable.b63,R.drawable.b64,R.drawable.b65,R.drawable.b66,R.drawable.b67,R.drawable.b68,R.drawable.b69,R.drawable.b70,R.drawable.b71,R.drawable.b72,R.drawable.b73,R.drawable.b74,R.drawable.b75,R.drawable.b76,R.drawable.b77,R.drawable.b78,R.drawable.b79,R.drawable.b80,R.drawable.b81,R.drawable.b82,R.drawable.b83,R.drawable.b84,R.drawable.b85,R.drawable.b86,R.drawable.b87,R.drawable.b88,R.drawable.b89,R.drawable.b90,R.drawable.b91,R.drawable.b92,R.drawable.b93,R.drawable.b94,R.drawable.b95,R.drawable.b96,R.drawable.b97};
    int[] falalivangi = new int[]{R.drawable.e1,R.drawable.e2,R.drawable.e3,R.drawable.e4,R.drawable.e5,R.drawable.e6,R.drawable.e7,R.drawable.e8,R.drawable.e9,R.drawable.e10,R.drawable.e11,R.drawable.e12,R.drawable.e13,R.drawable.e14,R.drawable.e15,R.drawable.e16,R.drawable.e17,R.drawable.e18,R.drawable.e19,R.drawable.e20,R.drawable.e21,R.drawable.e22,R.drawable.e23,R.drawable.e24,R.drawable.e25,R.drawable.e26,R.drawable.e27,R.drawable.e28};
    int[] icepic = new int[]{R.drawable.g1,R.drawable.g2,R.drawable.g3,R.drawable.g4,R.drawable.g5,R.drawable.g6,R.drawable.g7,R.drawable.g8,R.drawable.g9,R.drawable.g10,R.drawable.g11,R.drawable.g12,R.drawable.g13,R.drawable.g14,R.drawable.g15,R.drawable.g16,R.drawable.g17,R.drawable.g18,R.drawable.g19,R.drawable.g20,R.drawable.g21,R.drawable.g22,R.drawable.g23,R.drawable.g24,R.drawable.g25,R.drawable.g26,R.drawable.g27,R.drawable.g28,R.drawable.g29,R.drawable.g30,R.drawable.g31,R.drawable.g32,R.drawable.g33,R.drawable.g34,R.drawable.g35,R.drawable.g36,R.drawable.g37,R.drawable.g38,R.drawable.g39,R.drawable.g40,R.drawable.g41,R.drawable.g42,R.drawable.g43,R.drawable.g44,R.drawable.g45};
    int[] healthy = new int[]{R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,R.drawable.s11,R.drawable.s12,R.drawable.s13,R.drawable.s14,R.drawable.s15,R.drawable.s16,R.drawable.s17,R.drawable.s18,R.drawable.s19,R.drawable.s20,R.drawable.s21,R.drawable.s22,R.drawable.s23,R.drawable.s24,R.drawable.s25,R.drawable.s26,R.drawable.s27};
    int[] festival = new int[]{R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4,R.drawable.t5,R.drawable.t6,R.drawable.t7,R.drawable.t8,R.drawable.t9,R.drawable.t10,R.drawable.t11,R.drawable.t12,R.drawable.t13,R.drawable.t14,R.drawable.t15,R.drawable.t16,R.drawable.t17,R.drawable.t18,R.drawable.t19,R.drawable.t20,R.drawable.t21,R.drawable.t22,R.drawable.t23,R.drawable.t24,R.drawable.t25,R.drawable.t26,R.drawable.t27,R.drawable.t28,R.drawable.t29,R.drawable.t30,R.drawable.t31,R.drawable.t32,R.drawable.t33,R.drawable.t34,R.drawable.t35,R.drawable.t36,R.drawable.t37,R.drawable.t38,R.drawable.t39,R.drawable.t40,R.drawable.t41,R.drawable.t42,R.drawable.t43,R.drawable.t44,R.drawable.t45,R.drawable.t46,R.drawable.t47,R.drawable.t48,R.drawable.t49,R.drawable.t50,R.drawable.t51,R.drawable.t52};
    int[] children = new int[]{R.drawable.k1,R.drawable.k2,R.drawable.k3,R.drawable.k4,R.drawable.k5,R.drawable.k6,R.drawable.k7,R.drawable.k8,R.drawable.k9,R.drawable.k10,R.drawable.k11,R.drawable.k12,R.drawable.k13,R.drawable.k14,R.drawable.k15,R.drawable.k16,R.drawable.k17,R.drawable.k18,R.drawable.k19,R.drawable.k20,R.drawable.k21,R.drawable.k22,R.drawable.k23,R.drawable.k24,R.drawable.k25,R.drawable.k26,R.drawable.k27,R.drawable.k28,R.drawable.k29,R.drawable.k30,R.drawable.k31,R.drawable.k32,R.drawable.k33,R.drawable.k34,R.drawable.k35,R.drawable.k36,R.drawable.k37,R.drawable.k38,R.drawable.k39,R.drawable.k40,R.drawable.k41,R.drawable.k42,R.drawable.k43,R.drawable.k44,R.drawable.k45,R.drawable.k46,R.drawable.k47,R.drawable.k48,R.drawable.k49,R.drawable.k50,R.drawable.k51,R.drawable.k52,R.drawable.k53,R.drawable.k54,R.drawable.k55,R.drawable.k56,R.drawable.k57,R.drawable.k58,R.drawable.k59,R.drawable.k60,R.drawable.k61,R.drawable.k62,R.drawable.k63,R.drawable.k64,R.drawable.k65,R.drawable.k66,R.drawable.k67};
    int[] rayta = new int[]{R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4,R.drawable.f5,R.drawable.f6,R.drawable.f7,R.drawable.f8,R.drawable.f9,R.drawable.f10,R.drawable.f11,R.drawable.f12};
    int[] diabitees = new int[]{R.drawable.r1,R.drawable.r2,R.drawable.r3,R.drawable.r4,R.drawable.r5,R.drawable.r6,R.drawable.r7,R.drawable.r8,R.drawable.r9,R.drawable.r10};

    public MainAdapter(MainActivity mainActivity, Database database) {
        activity = mainActivity;
        this.database = database;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.mainview,parent,false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(activity).load(pic[position]).into(holder.image);
        holder.textView.setText(name[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SecondActivity.class);
                intent.putExtra("image", pic[position]);
                intent.putExtra("recipe", name[position]);
                if (position == 0) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=352; i<395; i++) {
                        Model model1 = new Model();
                        model1.setName(models.get(i).getName());
                        model1.setSahitya(models.get(i).getSahitya());
                        model1.setKruti(models.get(i).getKruti());
                        model1.setType_id(models.get(i).getType_id());
                        model1.setRow_id(models.get(i).getRow_id());
                        modelList.add(model1);
                    }
                    B = rotipic;
                }
                else if (position == 1) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=273; i<352; i++) {
                        Model model2 = new Model();
                        model2.setName(models.get(i).getName());
                        model2.setSahitya(models.get(i).getSahitya());
                        model2.setKruti(models.get(i).getKruti());
                        model2.setType_id(models.get(i).getType_id());
                        model2.setRow_id(models.get(i).getRow_id());
                        modelList.add(model2);
                    }
                    B = sabjipic;
                }
                else if (position == 2) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=61; i<176; i++) {
                        Model model3 = new Model();
                        model3.setName(models.get(i).getName());
                        model3.setSahitya(models.get(i).getSahitya());
                        model3.setKruti(models.get(i).getKruti());
                        model3.setType_id(models.get(i).getType_id());
                        model3.setRow_id(models.get(i).getRow_id());
                        modelList.add(model3);
                    }
                    B = nastapic;
                }
                else if (position == 3) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=176; i<273; i++) {
                        Model model3 = new Model();
                        model3.setName(models.get(i).getName());
                        model3.setSahitya(models.get(i).getSahitya());
                        model3.setKruti(models.get(i).getKruti());
                        model3.setType_id(models.get(i).getType_id());
                        model3.setRow_id(models.get(i).getRow_id());
                        modelList.add(model3);
                    }
                    B = mithaipic;
                }
                else if (position == 4) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=395; i<423; i++) {
                        Model model3 = new Model();
                        model3.setName(models.get(i).getName());
                        model3.setSahitya(models.get(i).getSahitya());
                        model3.setKruti(models.get(i).getKruti());
                        model3.setType_id(models.get(i).getType_id());
                        model3.setRow_id(models.get(i).getRow_id());
                        modelList.add(model3);
                    }
                    B = falalivangi;
                }
                else if (position == 5) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=435; i<480; i++) {
                        Model model3 = new Model();
                        model3.setName(models.get(i).getName());
                        model3.setSahitya(models.get(i).getSahitya());
                        model3.setKruti(models.get(i).getKruti());
                        model3.setType_id(models.get(i).getType_id());
                        model3.setRow_id(models.get(i).getRow_id());
                        modelList.add(model3);
                    }
                    B = icepic;
                }
                else if (position == 6) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=854; i<881; i++) {
                        Model model3 = new Model();
                        model3.setName(models.get(i).getName());
                        model3.setSahitya(models.get(i).getSahitya());
                        model3.setKruti(models.get(i).getKruti());
                        model3.setType_id(models.get(i).getType_id());
                        model3.setRow_id(models.get(i).getRow_id());
                        modelList.add(model3);
                    }
                    B = healthy;
                }
                else if (position == 7) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=881; i<933; i++) {
                        Model model3 = new Model();
                        model3.setName(models.get(i).getName());
                        model3.setSahitya(models.get(i).getSahitya());
                        model3.setKruti(models.get(i).getKruti());
                        model3.setType_id(models.get(i).getType_id());
                        model3.setRow_id(models.get(i).getRow_id());
                        modelList.add(model3);
                    }
                    B = festival;
                }
                else if (position == 8) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=603; i<670; i++) {
                        Model model3 = new Model();
                        model3.setName(models.get(i).getName());
                        model3.setSahitya(models.get(i).getSahitya());
                        model3.setKruti(models.get(i).getKruti());
                        model3.setType_id(models.get(i).getType_id());
                        model3.setRow_id(models.get(i).getRow_id());
                        modelList.add(model3);
                    }
                    B = children;
                }
                else if (position == 9) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=423; i<435; i++) {
                        Model model3 = new Model();
                        model3.setName(models.get(i).getName());
                        model3.setSahitya(models.get(i).getSahitya());
                        model3.setKruti(models.get(i).getKruti());
                        model3.setType_id(models.get(i).getType_id());
                        model3.setRow_id(models.get(i).getRow_id());
                        modelList.add(model3);
                    }
                    B = rayta;
                }
                else if (position == 10) {
                    database = new Database(activity);
                    modelList = new ArrayList<>();
                    models = database.RetriveData();
                    for (int i=844; i<854; i++) {
                        Model model3 = new Model();
                        model3.setName(models.get(i).getName());
                        model3.setSahitya(models.get(i).getSahitya());
                        model3.setKruti(models.get(i).getKruti());
                        model3.setType_id(models.get(i).getType_id());
                        model3.setRow_id(models.get(i).getRow_id());
                        modelList.add(model3);
                    }
                    B = diabitees;
                }
                intent.putExtra("pic2",B);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView image;

        public MainHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.pic);
        }
    }
}
