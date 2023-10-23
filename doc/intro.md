# Introduction to game-of-life

TODO: write [great documentation](http://jacobian.org/writing/what-to-write/)


Project implements convey game-of-life
Its using clojure standard library and clojure wrapper around javafx: https://github.com/cljfx/cljfx
For the tests its utilising test-runner from: https://github.com/cognitect-labs/test-runner

To run application:
clj -M:run-m <runs with the default interval set to 1000ms> or
clj -M:run-m "{:interval_ms 100}" <runs with an interval set to 1000ms> or
clj -X:run-x <runs with the default interval set to 1000ms> or
clj -X:run-x "{:interval_ms 100}" <interval set to 100ms> - interval is the ui refresh rate 

To run tests: (TODO-fix it, atm tests don't run)
clj -X:test ...args...
