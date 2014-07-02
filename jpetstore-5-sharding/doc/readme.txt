
   1. Account Modular: Data volume is huge, virtical + horizonal sharding, 2 shards.
   2. Order Modular: Data volume is huge, virtical + horizonal sharding, 2 shards.
   3. Product Modular: Data volume is not huge, only virtical sharding, 1 shard only.
   
   For the sharding diagram:
   As a demo, we want to demo more different scenarios: We assume there are a lot of records in Product & Item tables,
   so, we dividing product modular into product & item shard and put them on the same database node is a typical pattern. 
   see: http://blog.csdn.net/bluishglc/article/details/7696085