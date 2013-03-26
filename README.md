# s3-util

Some utility functions for working with Amazon S3. Uses clj-aws-s3
underneath. This is a work in progress.

## Usage

Current leiningen dependency:

```clojure

[s3-util "0.1.0-SNAPSHOT"]
```

So far only one measly function exists:

```clojure

(def cred {:access-key "...", :secret-key "..."})

;; creates or overwrites "some-key" with "some-value" in "my-bucket"
;; "my-bucket" is automatically created if it does not already exist

(spit cred "my-bucket" "some-key" "some-value")

```

## License

Copyright © 2013 Greg Phillips

Distributed under the Eclipse Public License, the same as Clojure.
