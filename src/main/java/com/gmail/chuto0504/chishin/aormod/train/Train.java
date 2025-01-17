package com.gmail.chuto0504.chishin.aormod.train;

public class Train {
       // 列車の基本情報
       private String trainId;
       private TrainType type;
       private double speed;
       private boolean isManualMode;
       
       // 簡略化した蒸気機関車パラメータ
       private double steamPower;        // 蒸気圧（石炭と水を統合したパラメータ）
       private boolean fireboxActive;    // 火室の状態
       
       public Train(String trainId, TrainType type) {
           this.trainId = trainId;
           this.type = type;
           this.speed = 0.0;
           this.isManualMode = true;
           
           // 蒸気機関車の初期設定
           this.steamPower = 100.0;
           this.fireboxActive = false;
       }
       
       // 運転関連メソッド
       public void accelerate() {
           if (type == TrainType.STEAM_LOCOMOTIVE && fireboxActive) {
               speed = Math.min(speed + 2.0, getMaxSpeed());
               steamPower = Math.max(0, steamPower - 0.1); // 蒸気を少しずつ消費
           }
       }
       
       public void brake() {
           speed = Math.max(0, speed - 3.0);
       }
       
       public void stop() {
           speed = 0;
       }
       
       public void toggleDoors() {
           // ドアの開閉処理
       }
       
       public void setAutoPilot(boolean enabled) {
           this.isManualMode = !enabled;
       }
       
       // 簡略化した蒸気機関車メソッド
       public void refillSteamPower() {
           // 石炭と水を同時に補給（駅や補給所で使用）
           steamPower = 100.0;
       }
       
       public void toggleFirebox() {
           this.fireboxActive = !this.fireboxActive;
           if (fireboxActive && steamPower > 0) {
               // 火室を稼働させると自動的に適切な蒸気圧を維持
               steamPower = Math.max(0, steamPower - 0.05);
           }
       }
       
       private double getMaxSpeed() {
           return fireboxActive ? 40.0 : 0.0; // 火室が稼働していれば最高速度を出せる
       }
       
       // ゲッター
       public double getSteamPower() {
           return steamPower;
       }
       
       public boolean isFireboxActive() {
           return fireboxActive;
       }
} 