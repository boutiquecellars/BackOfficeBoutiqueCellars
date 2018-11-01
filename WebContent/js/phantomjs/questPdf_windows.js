var args = require('system').args;
var postBody = args[1];
var page = require('webpage').create();

//viewportSize being the actual size of the headless browser
page.viewportSize = { width: 1280, height: 768 };
page.open('http://localhost/Login','POST', postBody, function(status) {
  console.log('status: '+status);

 	var page2 = require('webpage').create();
  var postBody = args[2];	
  page2.viewportSize = { width: 1280, height: 768 };
  page2.open('http://localhost/index.jsp','POST', postBody, function(status) {
  		console.log('internal status: '+status);
      //document.body.setAttribute("class", "mini-navbar");
      
      page2.paperSize = {
            format: 'A4',
            orientation: 'portrait',
            margin: '0.0cm'
            
        };
      /*
      page2.paperSize = {
        width: '8.5in',
        height: '11in',
        header: {
          height: "1cm",
          contents: phantom.callback(function(pageNum, numPages) {
            return "<h3> <span style='float:right'>" + pageNum + " / " + numPages + "</span></h3>";
          })
        },
        footer: {
          height: "1cm",
          contents: phantom.callback(function(pageNum, numPages) {
            return "<h3> <span style='float:right'>" + pageNum + " / " + numPages + "</span></h3>";
          })
        }
      }*/

    setTimeout(function(){

        var x = page2.evaluate(function() {
             //zoom
             document.body.style.zoom="55%";
             // ocultando
             document.getElementsByTagName('nav')[0].setAttribute("class","none"); 
             document.getElementById('page-wrapper').setAttribute("class","menu-invisible");
             document.getElementById('flot-bar-chart').style.marginLeft="90px";
             //document.getElementById('flot-bar-chart').style.width="1400px";
             var elems = document.getElementById("flot-bar-chart").getElementsByClassName('flot-base');
            for(var i = 0; i < elems.length; i++) {
                elems[i].style.width = '1280px';
            }
            var elems = document.getElementById("flot-bar-chart").getElementsByClassName('flot-y2-axis');
            for(var i = 0; i < elems.length; i++) {
                elems[i].style.left = '410px';
            }
            
            document.getElementById('small').innerHTML="<br/>";
            document.getElementById('li-lista-cliente').style.display="none";
            document.getElementById('li-pdf').style.display="none";
            // document.body.setAttribute("class", "body-small");
             //document.body.setAttribute("class", "mini-navbar");
        });
         console.log(x);
  			  // era aqui
          page2.dpi=72; 
          page2.render('C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\ROOT\\js\\phantomjs\\q.pdf', {format: 'pdf', pool: 1,paperFormat : 'A4'});
          phantom.exit(); 
        }, 3000);
  		 
  });
  //phantom.exit();
});



