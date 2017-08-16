# LastTry

[![Build Status](https://travis-ci.org/egordorichev/LastTry.svg?branch=master)](https://travis-ci.org/egordorichev/LastTry) [![Join chat](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/last-try/Lobby) [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php) [![codebeat badge](https://codebeat.co/badges/92d0acff-0fd0-438b-b7a8-f40390f995df)](https://codebeat.co/projects/github-com-egordorichev-lasttry-dev) [![Code Climate](https://codeclimate.com/github/egordorichev/LastTry.png)](https://codeclimate.com/github/egordorichev/LastTry)

LastTry is open-source Terraria clone in Java, written using LibGDX library.
Our goal is to recreate original game with better perfomance, and old content.

### Building

Clone the repo:

```bash
git clone https://github.com/egordorichev/LastTry.git
cd LastTry
```

Compile project:

```bash
./gradlew clean build dist
```

### Running

You should end up with a jar, placed in `desktop/build/libs/`. Run it.

Here is a few options, you can play around:

* Launch app with `-nl` arg, to disable light and load game faster
* Launch app with `-d` arg, witch enables debug mode. In it you can press `/` and open dev console.

![Console](
http://i.imgur.com/Q2P7a42.png) 

* + Give your self some items, using console. You just simply type in console: `/give lt:dirt 100`. `lt:dirt` is dirt block id and 100 is count. Full list of items can be found here: https://github.com/egordorichev/LastTry/blob/dev/core/assets/data/items.json

#### TODO List

[TODO list can be found here](https://trello.com/b/MgdX6wA4/game-lasttry).

#### Docs

[Docs can be found here](https://egordorichev.github.io/ltdocs/index.html)

### Liecense

```
MIT License

Copyright (c) 2017 Egor Dorichev

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
