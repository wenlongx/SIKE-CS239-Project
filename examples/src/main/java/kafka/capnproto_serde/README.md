# Cap'nProto Ser De

To install the Cap'nProto compiler, do it from Github rather than through a package manager (some modifications need to be made):

```bash
$ git clone https://github.com/sandstorm-io/capnproto.git
$ cd capnproto/c++
$ autoreconf -i
$ ./configure
$ make -j6 check
$ sudo make install
```

Then get the Java compiler as follows:

```bash
$ git clone https://github.com/capnproto/capnproto-java.git
$ cd cmake
$ mkdir build
$ cd build
$ cmake -DCAPNP_PKG_PATH=$WORKDIR/capnproto/c++/pkgconfig/capnp.pc $WORKDIR/capnproto/c++/CMakeLists.txt

$ cd $WORKDIR/capnproto
$ make -j6
$ sudo make install
$ cd $WORKDIR/capnproto-java
$ make
$ cp capnpc-java /usr/local/bin 
```

This link provides some helpful insights as well: https://stackoverflow.com/questions/51795094/need-help-creating-and-installing-capnpc-java-capnproto-for-java

ie. `./capnp.sh capn_classes.capnp`