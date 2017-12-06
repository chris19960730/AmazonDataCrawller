const testFolder = './html/';
const fs = require('fs');
var path = require('path')

fs.readdir(testFolder, (err, files) => {
    console.log(files)
    var exclude = files.indexOf('.DS_Store')
    if (exclude > -1) {
        files.splice(exclude, 1);
    }
    var done = files.map(filename => filename.split('.').splice(0)[0]);
    var urls = fs.readFileSync(path.resolve(__dirname,'url.txt'), 'utf8').split(/\r?\n/).map(url => url.split('/').splice(-1)[0])
    console.log('')
    var todo = urls.filter(val => !done.includes(val));
    var result = [];

    for(var i=0,len=todo.length;i<len;i+=(todo.length/25)) {
       result.push(todo.slice(i,i+(todo.length/25)));
    }

    for(var i=0; i<result.length; i++){
        var w1 = fs.createWriteStream('./t'+i+".txt")
        for(var j=0; j<result[i].length; j++){
             w1.write(result[i][j]+'\n')
        }
    }
    
    for(var i=0; i<result.length; i++){
        console.log(i, ":", result[i].length)
    }
    console.log('done:', done.length, 'todo total:', todo.length, "total", urls.length)

})