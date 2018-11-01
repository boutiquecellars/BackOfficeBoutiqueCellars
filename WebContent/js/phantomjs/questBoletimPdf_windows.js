"use strict";
var args = require('system').args;
var system = require('system');
var postBody = system.args[1];//args[1];
var page = require('webpage').create();

//viewportSize being the actual size of the headless browser
page.viewportSize = { width: 1280, height: 768 };
page.open('http://localhost/Login','POST', postBody, function(status) {
  console.log('status: '+status);

 	var page2 = require('webpage').create();
  var postBody = system.args[2];//args[2];	
  page2.viewportSize = { width: 1280, height: 768 };
  page2.open('http://localhost/boletim.jsp','POST', postBody, function(status) {
  		console.log('internal status: '+status);
      //document.body.setAttribute("class", "mini-navbar");
      
      page2.paperSize = {
            format: 'A4',
            orientation: 'portrait',
             margin: {
            top: '20px',
            left: '0px'
          }
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
        //page2.zoomFactor = 0.75;
        var x = page2.evaluate(function() {
             //zoom
             //zoom
             document.body.style.zoom="50%";
             $("body").css("background-color","white");
             $(".navbar").hide();
             $(".pace").hide();
             $(".row.border-bottom").hide();
            // $("#row2").css("height","550px");
             $("#row3").css("page-break-before","always");
             $("#row3").css("margin-top","100px");
             $("#row5").css("page-break-before","always");
             $("#row7").css("page-break-before","always");
        });
        // console.log(x);
  			  // era aqui
          page2.dpi=72;
          //test
          //page2.render('C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\ROOT\\js\\phantomjs\\quest_boletim.pdf', {format: 'pdf', pool: 1,paperFormat : 'A4'});
          // production
          page2.render('C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\ROOT\\js\\phantomjs\\quest_boletim.pdf', {format: 'pdf', pool: 1,paperFormat : 'A4'});
            
        //page2.render('/usr/local/apache-tomcat-8.0.28/webapps/Quest/js/phantomjs/q.pdf', {format: 'pdf', pool: 1,paperFormat : 'A4'});
          
            phantom.exit();
          
        }, 15000);
  		 
  });
  //phantom.exit();
});



