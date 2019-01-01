# task-manager-api
## DBマイグレーション
Dockerコンテナで開発用、単体試験用のDBを分けて作成する。
DockerFileは以下：
ext/docker/dev, ext/docker/ut

## ビルド
以下の手順にて実行する
### dev
- docker build -t pg-task-base:1.0.0 .
- docker run --name pg-task-base -d -p 5532:5432 pg-task-base:1.0.0
- gradle flywayMigrate -i

### ut
- docker build -t ut-pg-task-base:1.0.0 .
- docker run --name ut-pg-task-base -d -p 15532:5432 ut-pg-task-base:1.0.0
- gradle migrateUtdb