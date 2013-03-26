(ns s3-util.core
  (:require [aws.sdk.s3 :as s3]))

(defn- create-bucket-if-needed
  "Creates a new S3 bucket with name \"bucket\" using AWS credentials
  \"cred\" if it does not already exist."
  [cred bucket]
  (if (not (s3/bucket-exists? cred bucket))
    (s3/create-bucket cred bucket)))

(defn spit
  "Puts a new S3 key/value pair into \"bucket\" using AWS credentials
  \"cred\". Creates the bucket automatically if it does not already
  exist. Passes metadata through to aws.sdk.s3/put-object."
  [cred bucket key value & [metadata]]
  (do
    (create-bucket-if-needed cred bucket)
    (s3/put-object cred bucket key value metadata)))
