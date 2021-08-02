(defproject travel-diary "0.1.0-SNAPSHOT"

  :description "Let's share your journey with the world!"
  :url "http://example.com/FIXME"

  :dependencies [[bouncer "1.0.0"]
                 [buddy/buddy-auth "3.0.1"]
                 [buddy/buddy-core "1.10.1"]
                 [buddy/buddy-hashers "1.8.1"]
                 [buddy/buddy-sign "3.4.1"]
                 [ch.qos.logback/logback-classic "1.2.5"]
                 [cljs-ajax "0.8.3"]
                 [clojure.java-time "0.3.2"]
                 [com.cognitect/transit-clj "1.0.324"]
                 [com.cognitect/transit-cljs "0.8.269"]
                 [com.layerware/hugsql "0.5.1"]
                 [conman "0.9.1"]
                 [cprop "0.1.18"]
                 [day8.re-frame/http-fx "0.2.3"]
                 [expound "0.8.9"]
                 [funcool/struct "1.4.0"]
                 [json-html "0.4.7"]
                 [luminus-migrations "0.7.1"]
                 [luminus-transit "0.1.2"]
                 [luminus-undertow "0.1.11"]
                 [luminus/ring-ttl-session "0.3.3"]
                 [markdown-clj "1.10.5"]
                 [metosin/muuntaja "0.6.8"]
                 [metosin/reitit "0.5.13"]
                 [metosin/ring-http-response "0.9.2"]
                 [mount "0.1.16"]
                 [nrepl "0.8.3"]
                 [org.clojure/clojure "1.10.3"]
                 [org.clojure/clojurescript "1.10.879" :scope "provided"]
                 [org.clojure/core.async "1.3.618"]
                 [org.clojure/java.jdbc "0.4.2"]
                 [org.clojure/tools.cli "1.0.206"]
                 [org.clojure/tools.logging "1.1.0"]
                 [org.postgresql/postgresql "42.2.23"]
                 [org.webjars.npm/bulma "0.9.2"]
                 [org.webjars.npm/material-icons "0.7.0"]
                 [org.webjars/webjars-locator "0.41"]
                 [org.webjars/webjars-locator-jboss-vfs "0.1.0"]
                 [cljsjs/reactstrap "7.1.0-0"]
                 [cljsjs/react-select "2.4.4-0"]
                 [re-frame "1.2.0"]
                 [reagent "1.1.0"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-core "1.9.4"]
                 [ring/ring-defaults "0.3.3"]
                 [selmer "1.12.44"]
                 [thheller/shadow-cljs "2.15.2" :scope "provided"]]

  :min-lein-version "2.0.0"

  :source-paths ["src/clj" "src/cljs" "src/cljc"]
  :test-paths ["test/clj"]
  :resource-paths ["resources" "target/cljsbuild"]
  :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                             :compiler {:main          "travel-diary.app"
                                        :asset-path    "/js/out"
                                        :output-to     "target/cljsbuild/public/js/app.js"
                                        :output-dir    "target/cljsbuild/public/js/out"
                                        :optimizations :none
                                        :source-map    true
                                        :pretty-print  true}}
                       :min
                       {:source-paths ["src/cljs"]
                        :compiler
                        {:output-to     "target/cljsbuild/public/js/app.js"
                         :output-dir    "target/uberjar"
                         :externs       ["react/externs/react.js"]
                         :optimizations :advanced
                         :pretty-print  false}}}}

  :target-path "target/%s/"
  :main ^:skip-aot travel-diary.core

  :plugins [[lein-cljsbuild "1.1.3"]]
  :clean-targets ^{:protect false}
  [:target-path "target/cljsbuild"]


  :profiles
  {:uberjar {:omit-source true

             :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
             :aot :all
             :uberjar-name "travel-diary.jar"
             :source-paths ["env/prod/clj"  "env/prod/cljs"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev  {:jvm-opts ["-Dconf=dev-config.edn"]
                  :dependencies [[binaryage/devtools "1.0.3"]
                                 [cider/piggieback "0.5.2"]
                                 [pjstadig/humane-test-output "0.11.0"]
                                 [prone "2021-04-23"]
                                 [re-frisk "1.5.1"]
                                 [ring/ring-devel "1.9.4"]
                                 [ring/ring-mock "0.4.0"]]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.24.1"]
                                 [jonase/eastwood "0.3.5"]
                                 [cider/cider-nrepl "0.26.0"]
                                 [lein-figwheel "0.5.19"]]


                  :source-paths ["env/dev/clj"  "env/dev/cljs" "test/cljs"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user
                                 :timeout 120000}
                  :injections [(require 'pjstadig.humane-test-output)
                               (pjstadig.humane-test-output/activate!)]}
   :project/test {:jvm-opts ["-Dconf=test-config.edn"]
                  :resource-paths ["env/test/resources"]}
   :profiles/dev {:env {:database-url "postgresql://localhost/travel_diary?user=postgres&password=secretpassword"}}
   :profiles/test {}})
