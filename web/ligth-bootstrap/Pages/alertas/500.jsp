<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
        <%@include file="../includes/cssInclude.jsp" %>  
        <title>Error 500 - SaleslandColombia</title>
        <style>
            
            @import url(https://fonts.googleapis.com/css?family=Raleway:300);
            *, *:before, *:after {
              box-sizing: border-box;
            }
            html {
              height: 100%;
            }
            body {
             
              background-size: cover;
              font-family: 'Raleway', sans-serif;
              background-color: #006A85;
              height: 100%;
            }
            .text-wrapper {
              /*height: 100%;*/
              display: flex;
              flex-direction: column;
              align-items: center;
              justify-content: center;
            }
            .title {
              font-size: 9em;
              font-weight: 700;
              color: white;
              position: relative;
            }
            .subtitle {
              font-size: 40px;
              font-weight: 700;
              color: white;
              position: relative;
            }
            .buttons {
              margin: 30px;
            }
            .buttons a.button {
              background-color: #006A85;
              font-weight: 700;
              border: 2px solid white;
              text-decoration: none;
              padding: 15px;
              text-transform: uppercase;
              color: white;
              border-radius: 26px;
              transition: all 0.2s ease-in-out;
            }
            .buttons a.button:hover {
              background-color: #0090B5;
              color: white;
              transition: all 0.2s ease-in-out;
            }
            @keyframes noise-anim {
              0% {
                clip: rect(181px, 9999px, 33px, 0);
              }
              5% {
                clip: rect(87px, 9999px, 75px, 0);
              }
              10% {
                clip: rect(200px, 9999px, 106px, 0);
              }
              15% {
                clip: rect(128px, 9999px, 198px, 0);
              }
              20% {
                clip: rect(119px, 9999px, 191px, 0);
              }
              25% {
                clip: rect(19px, 9999px, 16px, 0);
              }
              30% {
                clip: rect(56px, 9999px, 179px, 0);
              }
              35% {
                clip: rect(147px, 9999px, 55px, 0);
              }
              40% {
                clip: rect(111px, 9999px, 99px, 0);
              }
              45% {
                clip: rect(57px, 9999px, 92px, 0);
              }
              50% {
                clip: rect(70px, 9999px, 134px, 0);
              }
              55% {
                clip: rect(74px, 9999px, 131px, 0);
              }
              60% {
                clip: rect(17px, 9999px, 132px, 0);
              }
              65% {
                clip: rect(43px, 9999px, 151px, 0);
              }
              70% {
                clip: rect(27px, 9999px, 124px, 0);
              }
              75% {
                clip: rect(171px, 9999px, 188px, 0);
              }
              80% {
                clip: rect(121px, 9999px, 199px, 0);
              }
              85% {
                clip: rect(35px, 9999px, 167px, 0);
              }
              90% {
                clip: rect(136px, 9999px, 23px, 0);
              }
              95% {
                clip: rect(99px, 9999px, 90px, 0);
              }
              100% {
                clip: rect(200px, 9999px, 9px, 0);
              }
            }
            .subtitle:after, .title:after {
              content: attr(data-content);
              position: absolute;
              left: 2px;
              text-shadow: -1px 0 red;
              top: 0;
              color: white;
              overflow: hidden;
              clip: rect(0, 900px, 0, 0);
              animation: noise-anim 2s infinite linear alternate-reverse;
            }
            @keyframes noise-anim-2 {
              0% {
                clip: rect(109px, 9999px, 179px, 0);
              }
              5% {
                clip: rect(157px, 9999px, 152px, 0);
              }
              10% {
                clip: rect(175px, 9999px, 116px, 0);
              }
              15% {
                clip: rect(30px, 9999px, 1px, 0);
              }
              20% {
                clip: rect(170px, 9999px, 74px, 0);
              }
              25% {
                clip: rect(132px, 9999px, 101px, 0);
              }
              30% {
                clip: rect(53px, 9999px, 179px, 0);
              }
              35% {
                clip: rect(15px, 9999px, 56px, 0);
              }
              40% {
                clip: rect(35px, 9999px, 81px, 0);
              }
              45% {
                clip: rect(144px, 9999px, 72px, 0);
              }
              50% {
                clip: rect(55px, 9999px, 168px, 0);
              }
              55% {
                clip: rect(32px, 9999px, 10px, 0);
              }
              60% {
                clip: rect(159px, 9999px, 154px, 0);
              }
              65% {
                clip: rect(140px, 9999px, 46px, 0);
              }
              70% {
                clip: rect(14px, 9999px, 113px, 0);
              }
              75% {
                clip: rect(168px, 9999px, 85px, 0);
              }
              80% {
                clip: rect(124px, 9999px, 94px, 0);
              }
              85% {
                clip: rect(163px, 9999px, 191px, 0);
              }
              90% {
                clip: rect(51px, 9999px, 75px, 0);
              }
              95% {
                clip: rect(189px, 9999px, 185px, 0);
              }
              100% {
                clip: rect(175px, 9999px, 185px, 0);
              }
            }
            .subtitle:before, .title:before {
              content: attr(data-content);
              position: absolute;
              left: -2px;
              text-shadow: 1px 0 blue;
              top: 0;
              color: white;
              overflow: hidden;
              clip: rect(0, 900px, 0, 0);
              animation: noise-anim-2 3s infinite linear alternate-reverse;
            }
        </style>        
    </head>
    <body>
                                
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute">
            <div class="container">
                <div class="navbar-wrapper">
                    <a href="/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/login.jsp" class="navbar-brand"><img style="height: 30px; white-space: 100px;" src="../../assets/img/LOGO_SALESLAND_NEGATIVO.png"></a>
                    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-bar burger-lines"></span>
                        <span class="navbar-toggler-bar burger-lines"></span>
                        <span class="navbar-toggler-bar burger-lines"></span>
                    </button>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->        
        <br><br><br>
        <div class="text-wrapper">
            <div class="title" data-content="500">
                500
            </div>

            <div class="subtitle" data-content="Ocurrió un error en el servidor, por favor intentalo más tarde.">
                Ocurrió un error en el servidor, por favor intentalo más tarde.
            </div>

            <div class="buttons">
                <a class="button" href="/SaleslandColombiaApp/ligth-bootstrap/Pages/usuario/login.jsp">Volver al inicio</a>
            </div>
        </div>
                        
    </body>
</html>
