# Używanie bazy PostGIS

## Instalacja:

```
sudo apt-get install postgresql postgis postgresql-9.3-pgrouting osm2pgsql osm2pgrouting postgresql-contrib
```

1. Download a .osm file
2. Connect to database.
3. Create new database (osm_database)
4. Create extensions: hstore pgrouting plpgsql postigs postgis_topology
5. Download osm file (map.osm)
6. run: osm2pgrouting -file map.osm -conf /usr/share/osm2pgrouting/mapconfig.xml -dbname osm_database -user postgres -passwd password -clean
7. Database should be imported

GUI:
```
sudo apt-get install qgis
```

Osm2po
* http://osm2po.de/dld/osm2po-5.0.0.zip

# OSM
* https://wiki.openstreetmap.org/wiki/Develop
* https://wiki.openstreetmap.org/wiki/OSM_XML
* The most interesting entities are:
  * https://wiki.openstreetmap.org/wiki/Node
  * https://wiki.openstreetmap.org/wiki/Way
