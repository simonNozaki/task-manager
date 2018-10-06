# task-manager-web
タスク管理システムのうちウェブブラウザアプリケーションです。
データをJava APIとやりとりして、データベースの読み書きをAPIが担い、ブラウザアプリはデータの入力および表示を担います。

# 目次
1. 要素技術
2. 全体の設計思想
3. Angularによる開発の注意点およびハマりどころ
4. 
5. 

＃ 1. 要素技術
## task-magaer-api
- 言語　：　Java, 1.8
- Framework　：　Spring, 1.5.10
- ORM　：　Mybatis
- Build　：　Gradle, 4.10
## task-manager-web

#　2. 設計思想
##　データの処理
同じディレクトリにあるtask-manager-api（Javaアプリ）がデータの読み書きを実際に実施します。
Angularアプリであるtask-manager-webは
