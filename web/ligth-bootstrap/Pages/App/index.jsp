<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <%@include file="../../Pages/includes/cssInclude.jsp" %>
    <title>Inicio de sesion - SaleslandColombia</title>
    
    <style>        
            @import url(https://fonts.googleapis.com/css?family=Montserrat+Alternates:400,700);
            @import url(https://fonts.googleapis.com/css?family=Eczar);
            
            /** sass/scss/less code */

            #container {
                display: block;
                width: 100%;
                height: 100%;
                padding-top: 84px;
                box-sizing: border-box;
            }

            #loader {
                display: block;
                position: absolute;
                top: 50%;
                left: 50%;
                width: 58px;
                height: 86px;
                margin-top: -43px;
                margin-left: -29px;
                font-family: 'Montserrat Alternates', sans-serif;
                line-height: 42px;
                overflow: hidden;
                opacity: 0;
                animation: Loader 5s ease-in-out infinite;

            }

            #loader * {
                display: block;
                position: absolute;
            }

            @keyframes Loader {
                5%, 100% {
                    opacity: 1;
                }
                30% {
                    width: 58px;
                    margin-left: -29px;
                }
                40%, 82% {
                    width: 282px;
                    margin-left: -172px;
                }
                86%, 100% {
                    width: 4px;
                    margin-left: 2px;
                }
            }

            #loader .rectangle {
                left: 0;
                bottom: 20px;
                width: 58px;
                height: 66px;
                border: 2px solid #8D8E8F;
                overflow: hidden;
                box-sizing: border-box;
                background-color: #fff;
            }

            #loader .rectangle span {
                left: 24px;
                bottom: 3px;
                font-size: 36px;
                font-weight: 700;
                color: #8D8E8F;
                animation: FillSpan 5s ease-in-out infinite;
            }

            #loader .rectangle.outlines {
                animation: Outlines 5s ease-in-out infinite;
            }

            #loader .rectangle.fill {
                height: 4px;
                border-color: transparent;
                background-color: #8D8E8F;
                animation: Fill 5s ease-in-out infinite;
            }

            #loader .rectangle.fill span {
                color: #fff;
            }

            @keyframes Outlines {
                82% {
                    width: 58px;
                    height: 66px;
                    bottom: 20px;
                }
                85%, 100% {
                    width: 4px;
                    bottom: 0;
                }
                85%, 86% {
                    height: 86px;
                    border-width: 2px;
                }
                89%, 100% {
                    height: 0;
                    border-width: 0;
                }
            }

            @keyframes Fill {
                30%, 100% {
                    height: 66px;
                }
                30% {
                    background-color: #e79723;
                }
                40%, 100% {
                    background-color: #00ACC0;
                }
                82% {
                    width: 58px;
                    height: 66px;
                    bottom: 20px;
                }
                85%, 100% {
                    width: 4px;
                    bottom: 0;
                }
                85%, 86% {
                    height: 86px;
                    border-width: 2px;
                }
                89%, 100% {
                    height: 0;
                    border-width: 0;
                }
            }

            @keyframes FillSpan {
                80% {
                    left: 24px;
                }
                84%, 100% {
                    left: -34px;
                }
                82% {
                    bottom: 3px;
                }
                85%, 100% {
                    bottom: 9px;
                }
            }

            #loader .rinkin_coffee {
                left: -220px;
                top: 19px;
                width: 220px;
                animation: rinkinCoffee 5s ease-in-out infinite;
            }

            #loader .rinkin_coffee span {
                display: inline-block;
                position: static;
                color: #FFFFFF;
            }

            #loader .rinkin_coffee span.rinkin {
                font-size: 36px;
                font-weight: 700;
            }

            #loader .rinkin_coffee span.coffee {
                font-family: 'Eczar', serif;
                font-size: 41px;
                font-weight: 400;
            }

            @keyframes rinkinCoffee {
                30% {
                    left: -220px;
                }
                40%, 82% {
                    left: 62px;
                }
                86%, 100% {
                    left: -260px;
                }
            }

            #loader .slogan {
                right: 2px;
                bottom: 0;
                width: 165px;
                font-size: 12px;
                line-height: 16px;
                color: #FFFFFF;
                opacity: 0;
                animation: Slogan 5s ease-in-out infinite;
            }

            @keyframes Slogan {
                42% {
                    opacity: 0;
                }
                48%, 100% {
                    opacity: 1;
                }
                81% {
                    right: 2px;
                }
                86%, 100% {
                    right: 58px;
                }
            }



        </style>
    
</head>

<body>
    <div class="wrapper wrapper-full-page">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute">
            <div class="container">
                <div class="navbar-wrapper">
                    <a class="navbar-brand" href="#pablo"><strong>SALESLAND COLOMBIA</strong></a>
                    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-bar burger-lines"></span>
                        <span class="navbar-toggler-bar burger-lines"></span>
                        <span class="navbar-toggler-bar burger-lines"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse justify-content-end" id="navbar">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a href="../dashboard.html" class="nav-link">
                                <i class="material-icons" style="font-size: 25px;">dashboard</i>Dashboard                                
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a href="register.html" class="nav-link">
                                <i class="material-icons" style="font-size: 25px;">person_add</i>Register
                            </a>
                        </li>
                        <li class="nav-item  active ">
                            <a href="login.html" class="nav-link">
                                <i class="material-icons" style="font-size: 25px;">fingerprint</i>Login
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a href="lock.html" class="nav-link">
                                <i class="material-icons" style="font-size: 25px;">lock</i>Lock
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- End Navbar -->
        <div class="full-page  section-image" data-color="black" data-image="../../assets/img/SpaceBackground.jpg">
            <!--   you can change the color of the filter page using: data-color="blue | purple | green | orange | red | rose " -->
            <div class="content">
                <div class="container">
                    
                    <div class="col-md-4 ml-auto mr-auto">
                        <form class="form" method="" action="">
                            <div class="card card-login card-hidden">
                                <div class="card-header ">
                                    <h3 class="header text-center">Inicio de sesión</h3>
                                </div>
                                <div class="card-body ">
                                    <div class="card-body">
                                        <div class="form-group">
                                            <label>Email o documento</label>
                                            <input type="email" placeholder="Ingrese el email o el documento" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label>Contraseña</label>
                                            <input type="password" placeholder="Password" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="checkbox" value="" checked>
                                                    <span class="form-check-sign"></span>
                                                    Subscribe to newsletter
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-footer ml-auto mr-auto">
                                    <button type="submit" class="btn btn-warning btn-wd">Login</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    
                    <section id="container">
                        <div id="loader">
                            <div class="rinkin_coffee">
                                <span class="rinkin">
                                    alesland
                                </span>
                            </div>
                            <span class="slogan">
                                #HastaLaLunaNoParamos
                            </span>
                            <div class="rectangle outlines">
                                <span>S</span>
                            </div>
                            <div class="rectangle fill">
                                <span>S</span>
                            </div>
                        </div>
                    </section>
                    
                    
                </div>
            </div>
        </div>
        <%@include file="../../Pages/includes/footer.jsp" %>
    </div>    
</body>
<%@include file="../../Pages/includes/jsInclude.jsp" %>
<script>
    $(document).ready(function() {
        demo.checkFullPageBackgroundImage();

        setTimeout(function() {
            // after 1000 ms we add the class animated to the login/register card
            $('.card').removeClass('card-hidden');
            $("#container").hide();
        }, 4500)
    });
</script>
</html>