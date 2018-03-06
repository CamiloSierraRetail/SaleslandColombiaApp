<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Light Bootstrap Dashboard PRO - Premium Bootstrap 4 Admin Template by Creative Tim</title>
    <%@include file="../../Pages/includes/cssInclude.jsp" %>
</head>
<body>
    <div class="wrapper">
        <%@include file="../../Pages/includes/navLateral.jsp" %>
        <div class="main-panel">
            <!-- Navbar -->
            <%@include file="../../Pages/includes/navSuperior.jsp" %>
            <!-- End Navbar -->
            <div class="content">
                <div class="container-fluid">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-8 ml-auto mr-auto">
                                <form id="wizardFormulario" method="" action="">
                                    <div class="card card-wizard">
                                        <div class="card-header ">
                                            <h3 class="card-title text-center">Awesome Wizard</h3>
                                            <p class="card-category text-center">Split a complicated flow in multiple steps</p>
                                        </div>
                                        <div class="card-body ">
                                            <ul class="nav nav-tabs">
                                                <li class="nav-item">
                                                    <a class="nav-link active" href="#tab1" data-toggle="tab" role="tab" aria-controls="tab1" aria-selected="true">First Tab</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="#tab2" data-toggle="tab" role="tab" aria-controls="tab2" aria-selected="true">Second Tab</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" href="#tab3" data-toggle="tab" role="tab" aria-controls="tab3" aria-selected="true">Third Tab</a>
                                                </li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane fade show active" id="tab1" role="tabpanel">
                                                    <p class="text-center">Please tell us more about yourself.</p>
                                                    <div class="row justify-content-center">
                                                        <div class="col-md-5 ">
                                                            <div class="form-group">
                                                                <label class="control-label">First Name</label>
                                                                <input class="form-control" type="text" name="first_name" placeholder="ex: Mike" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-5">
                                                            <div class="form-group">
                                                                <label class="control-label">Last Name</label>
                                                                <input class="form-control" type="text" name="last_name" required="true" placeholder="ex: Andrew" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row justify-content-center">
                                                        <div class="col-md-10">
                                                            <div class="form-group">
                                                                <label class="control-label">Email
                                                                    <star>*</star>
                                                                </label>
                                                                <input class="form-control" type="text" name="email" email="true" placeholder="ex: hello@creative-tim.com" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab2" role="tabpanel">
                                                    <h5 class="text-center">Please give us more details about your platform.</h5>
                                                    <div class="row justify-content-center">
                                                        <div class="col-md-10">
                                                            <div class="form-group">
                                                                <label class="control-label">Your Website
                                                                    <star>*</star>
                                                                </label>
                                                                <input class="form-control" type="text" name="website" url="true" placeholder="ex: http://www.creative-tim.com" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row justify-content-center">
                                                        <div class="col-md-5">
                                                            <div class="form-group">
                                                                <label class="control-label">Framework Type</label>
                                                                <input class="form-control" type="text" name="framework" placeholder="ex: http://www.creative-tim.com" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-5">
                                                            <div class="form-group">
                                                                <label class="control-label">Language
                                                                    <star>*</star>
                                                                </label>
                                                                <select name="cities" class="selectpicker" data-title="Single Select" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                    <option value="id">Bahasa Indonesia</option>
                                                                    <option value="ms">Bahasa Melayu</option>
                                                                    <option value="ca">Català</option>
                                                                    <option value="da">Dansk</option>
                                                                    <option value="de">Deutsch</option>
                                                                    <option value="en">English</option>
                                                                    <option value="es">Español</option>
                                                                    <option value="el">Eλληνικά</option>
                                                                    <option value="fr">Français</option>
                                                                    <option value="it">Italiano</option>
                                                                    <option value="hu">Magyar</option>
                                                                    <option value="nl">Nederlands</option>
                                                                    <option value="no">Norsk</option>
                                                                    <option value="pl">Polski</option>
                                                                    <option value="pt">Português</option>
                                                                    <option value="fi">Suomi</option>
                                                                    <option value="sv">Svenska</option>
                                                                    <option value="tr">Türkçe</option>
                                                                    <option value="is">Íslenska</option>
                                                                    <option value="cs">Čeština</option>
                                                                    <option value="ru">Русский</option>
                                                                    <option value="th">ภาษาไทย</option>
                                                                    <option value="zh">中文 (简体)</option>
                                                                    <option value="zh-TW">中文 (繁體)</option>
                                                                    <option value="ja">日本語</option>
                                                                    <option value="ko">한국어</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row justify-content-center">
                                                        <div class="col-md-5">
                                                            <div class="form-group">
                                                                <label class="control-label">Bootstrap Version</label>
                                                                <select name="versions" class="selectpicker" data-title="Single Select" data-style="btn-default btn-outline" data-menu-style="dropdown-blue">
                                                                    <option selected="" disabled="">- version -</option>
                                                                    <option value="1.1">Bootstrap 1.1</option>
                                                                    <option value="2.0">Bootstrap 2.0</option>
                                                                    <option value="3.0">Bootstrap 3.0</option>
                                                                    <option value="4.0">Bootstrap 4.0(alpha)</option>
                                                                    <option value="4.0">Bootstrap 4.0(beta)</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-5">
                                                            <div class="form-group">
                                                                <label class="control-label">Price</label>
                                                                <input class="form-control" type="text" name="price" placeholder="ex: 19.00" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab3" role="tabpanel">
                                                    <h2 class="text-center text-space">Yuhuuu!
                                                        <br>
                                                        <small> Click on "
                                                            <b>Finish</b>" to join our community</small>
                                                    </h2>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer text-center">
                                            <button type="button" class="btn btn-default btn-wd btn-back pull-left">Back</button>
                                            <button type="button" class="btn btn-info btn-wd btn-next pull-right">Next</button>
                                            <button type="button" class="btn btn-info btn-wd btn-finish pull-right" onclick="onFinishWizard()">Finish</button>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../../Pages/includes/footer.jsp" %>
        </div>
    </div>
</body>
<%@include file="../../Pages/includes/jsInclude.jsp" %>
<script>
    //demo.initLBDWizardo();
    function lolWizardodo() {
        alert("yes wizardodod");
        // Code for the Validator
        var $validator = $('#wizardFormulario').validate({
            rules: {
                email: {
                    required: true,
                    email: true,
                    minlength: 5
                },
                first_name: {
                    required: false,
                    minlength: 5
                },
                last_name: {
                    required: false,
                    minlength: 5
                },
                website: {
                    required: true,
                    minlength: 5,
                    url: true
                },
                framework: {
                    required: false,
                    minlength: 4
                },
                cities: {
                    required: true
                },
                price: {
                    number: true
                }
            },
            highlight: function(element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function(element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },

            // errorPlacement: function(error, element) {
            //     $(element).parent('div').addClass('has-danger');
            //  }
        });

        // Wizard Initialization
        $('.card-wizard').bootstrapWizard({
            'tabClass': 'nav nav-pills',
            'nextSelector': '.btn-next',
            'previousSelector': '.btn-previous',

            onNext: function(tab, navigation, index) {
                var $valid = $('#wizardFormulario').valid();
                if (!$valid) {
                    $validator.focusInvalid();
                    return false;
                }
            },

            onInit: function(tab, navigation, index) {
                //check number of tabs and fill the entire row
                var $total = navigation.find('li').length;
                var $wizard = navigation.closest('.card-wizard');

                $first_li = navigation.find('li:first-child a').html();
                $moving_div = $('<div class="moving-tab">' + $first_li + '</div>');
                $('.card-wizard .wizard-navigation').append($moving_div);

                refreshAnimation($wizard, index);

                $('.moving-tab').css('transition', 'transform 0s');
            },

            onTabClick: function(tab, navigation, index) {
                var $valid = $('#wizardFormulario').valid();

                if (!$valid) {
                    return false;
                } else {
                    return true;
                }
            },

            onTabShow: function(tab, navigation, index) {
                var $total = navigation.find('li').length;
                var $current = index + 1;

                var $wizard = navigation.closest('.card-wizard');

                // If it's the last tab then hide the last button and show the finish instead
                if ($current >= $total) {
                    $($wizard).find('.btn-next').hide();
                    $($wizard).find('.btn-finish').show();
                } else {
                    $($wizard).find('.btn-next').show();
                    $($wizard).find('.btn-finish').hide();
                }

                button_text = navigation.find('li:nth-child(' + $current + ') a').html();

                setTimeout(function() {
                    $('.moving-tab').text(button_text);
                }, 150);

                var checkbox = $('.footer-checkbox');

                if (!index == 0) {
                    $(checkbox).css({
                        'opacity': '0',
                        'visibility': 'hidden',
                        'position': 'absolute'
                    });
                } else {
                    $(checkbox).css({
                        'opacity': '1',
                        'visibility': 'visible'
                    });
                }

                refreshAnimation($wizard, index);
            }
        });


        // Prepare the preview for profile picture
        $("#wizard-picture").change(function() {
            readURL(this);
        });

        $('[data-toggle="wizard-radio"]').click(function() {
            wizard = $(this).closest('.card-wizard');
            wizard.find('[data-toggle="wizard-radio"]').removeClass('active');
            $(this).addClass('active');
            $(wizard).find('[type="radio"]').removeAttr('checked');
            $(this).find('[type="radio"]').attr('checked', 'true');
        });

        $('[data-toggle="wizard-checkbox"]').click(function() {
            if ($(this).hasClass('active')) {
                $(this).removeClass('active');
                $(this).find('[type="checkbox"]').removeAttr('checked');
            } else {
                $(this).addClass('active');
                $(this).find('[type="checkbox"]').attr('checked', 'true');
            }
        });

        $('.set-full-height').css('height', 'auto');

        //Function to show image before upload

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    $('#wizardPicturePreview').attr('src', e.target.result).fadeIn('slow');
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        $(window).resize(function() {
            $('.card-wizard').each(function() {
                $wizard = $(this);

                index = $wizard.bootstrapWizard('currentIndex');
                refreshAnimation($wizard, index);

                $('.moving-tab').css({
                    'transition': 'transform 0s'
                });
            });
        });

        function refreshAnimation($wizard, index) {
            $total = $wizard.find('.nav li').length;
            $li_width = 100 / $total;

            total_steps = $wizard.find('.nav li').length;
            move_distance = $wizard.width() / total_steps;
            index_temp = index;
            vertical_level = 0;

            mobile_device = $(document).width() < 600 && $total > 3;

            if (mobile_device) {
                move_distance = $wizard.width() / 2;
                index_temp = index % 2;
                $li_width = 50;
            }

            $wizard.find('.nav li').css('width', $li_width + '%');

            step_width = move_distance;
            move_distance = move_distance * index_temp;

            $current = index + 1;

            if ($current == 1 || (mobile_device == true && (index % 2 == 0))) {
                move_distance -= 8;
            } else if ($current == total_steps || (mobile_device == true && (index % 2 == 1))) {
                move_distance += 8;
            }

            if (mobile_device) {
                vertical_level = parseInt(index / 2);
                vertical_level = vertical_level * 38;
            }

            $wizard.find('.moving-tab').css('width', step_width);
            $('.moving-tab').css({
                'transform': 'translate3d(' + move_distance + 'px, ' + vertical_level + 'px, 0)',
                'transition': 'all 0.5s cubic-bezier(0.29, 1.42, 0.79, 1)'

            });
        }
    }
</script>
<script>
    $(document).ready(function (){
        
       lolWizardodo(); 
    });
</script>
</html>