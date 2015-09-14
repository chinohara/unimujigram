# unimujigram
unimujigram's repository

## 構成
Pass heroku


DB AWS DynamoDB

## herokuの環境変数の設定

INSTAGRAM_CLIENT_API_KEY = <InstagramAPIのClientId>


AWS_ACCESS_KEY_ID = <AWSのAccessKeyId>


AWS_SECRET_ACCESS_KEY = <AWSのSecretAccessKey>



## ローカル開発時は"env.properties"を作成、下記プロパティを定義

apiClientId = <InstagramAPIのClientId>


awsAccessKeyId = <AWSのAccessKeyId>


awsSecretAccessKey = <AWSのSecretAccessKey>



## ローカル開発時はlibフォルダを作成し、以下Jarを格納して読み込む

aws-java-sdk-core-1.9.0.jar


aws-java-sdk-dynamodb-1.9.0.jar


aws-java-sdk-s3-1.9.0.jar



