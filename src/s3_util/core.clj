(ns s3-util.core
  (:require [amazonica.aws.s3 :as s3])
  (:refer-clojure :exclude [spit]))

(defn- create-bucket-if-needed
  "Creates a new S3 bucket with name \"bucket\" using AWS credentials
  \"cred\" if it does not already exist."
  [cred bucket]
  (if (not (s3/does-bucket-exist cred bucket))
    (s3/create-bucket cred bucket)))

(defn spit
  "Puts a new S3 key/value pair into \"bucket\" using AWS credentials
  \"cred\". Creates the bucket automatically if it does not already
  exist."
  [cred bucket key value & [metadata]]
  (do
    (create-bucket-if-needed cred bucket)
    (s3/put-object cred
                   :bucket-name bucket
                   :key key
                   :input-stream value
                   :metadata metadata)))

(defn spit-map
  "Puts a new S3 key/value pair into \"bucket\" using AWS credentials
  \"cred\" for each key/value pair in map. Creates the bucket
  automatically if it does not already exist."
  [cred bucket m & [metadata]]
  (do
    (create-bucket-if-needed cred bucket)
    (map (fn [[k v]] (s3/put-object cred
                                    :bucket-name bucket
                                    :key k
                                    :input-stream v
                                    :metadata metadata)) m)))
