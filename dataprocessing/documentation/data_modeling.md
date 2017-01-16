**Data model**

* Lamda
    - Batch layer
        * model
            - flat table
        * storage
            - JSON compressed vs parquet ?
            -
    - Service layer
        * storage
            - HBase
            - Elasticsearch

    - Speed layer
        * computation: Algebird (Hyperloglog)
        * storage
            - HBase
            - Elasticsearch

* Format

* Type
    - Batch
    - Realtime

* Techniques
    - Elastic search (Hadoop) & Kibana
    - Zeppelin
    - HBase, aerospike


**User analysis indicators**
1. Unique visitors , page views (+ stateless real-time )
    - unique visitors
        * new user of the past with first access time
        * unique *new* visitors during a short timeframe
        * unique visitors short timeframe

2. URL clicks  (+ stateless stateful real-time )
3. Statistics with in an user session(+ statefull real-time), [spark streaming blog](http://asyncified
.io/2016/07/31/exploring-stateful-streaming-with-apache-spark/)
4. User retention ratio,(1,3,7 days)
5. User Conversation Rate, churn rate
6. Other user action indicators: http://www.docin.com/p-1381878592.html