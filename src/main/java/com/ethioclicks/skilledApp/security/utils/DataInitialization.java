package com.ethioclicks.skilledApp.security.utils;//package com.zegulit.backend.security.utils;
//
//import com.zegulit.backend.businesslogic.entity.*;
//
//import com.zegulit.backend.businesslogic.enums.DAY_OF_WEEK;
//import com.zegulit.backend.businesslogic.enums.FEEDBACK_TYPE;
//import com.zegulit.backend.businesslogic.enums.SOCIAL_LINK_TYPE;
//import com.zegulit.backend.businesslogic.service.*;
//import com.zegulit.backend.security.entity.Role;
//import com.zegulit.backend.security.entity.User;
//import com.zegulit.backend.security.enums.RoleEnum;
//import com.zegulit.backend.security.model.NewUserDetail;
//import com.zegulit.backend.security.service.UserRegistrationService;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@Component
//public class DataInitialization {
//
//
//    private final UserRegistrationService userRegistrationService;
//    private final ShopService shopService;
//    private final CategoryService categoryService;
//    private final ProductService productService;
//    private final FavoritesService favoritesService;
//    private final ProductFeedbackService productFeedbackService;
//    private final ShopFeedbackService shopFeedbackService;
//    private final RateProductService rateProductService;
//    private final RateShopService rateShopService;
//    private final ProductQuestionAndAnswerService productQuestionAndAnswerService;
//    private final ShopQuestionAndAnswerService shopQuestionAndAnswerService;
//
//    public DataInitialization(UserRegistrationService userRegistrationService, ShopService shopService, CategoryService categoryService, ProductService productService, FavoritesService favoritesService, ProductFeedbackService productFeedbackService, ShopFeedbackService shopFeedbackService, RateProductService rateProductService, RateShopService rateShopService, ProductQuestionAndAnswerService productQuestionAndAnswerService, ShopQuestionAndAnswerService shopQuestionAndAnswerService) {
//        this.userRegistrationService = userRegistrationService;
//        this.shopService = shopService;
//        this.categoryService = categoryService;
//        this.productService = productService;
//        this.favoritesService = favoritesService;
//        this.productFeedbackService = productFeedbackService;
//        this.shopFeedbackService = shopFeedbackService;
//        this.rateProductService = rateProductService;
//        this.rateShopService = rateShopService;
//        this.productQuestionAndAnswerService = productQuestionAndAnswerService;
//        this.shopQuestionAndAnswerService = shopQuestionAndAnswerService;
//    }
//
//
//    @PostConstruct
//    void init(){
//
////        Category category = new Category(1L,"Electronic","Mobile","https://image.shutterstock.com/image-illustration/collection-consumer-electronics-flying-air-260nw-738107467.jpg");
////        categoryRepo.save(category);
//        Role adminRole=new Role(RoleEnum.ADMIN);
//        Set<Role> adminRoleset=new HashSet<>();
//        adminRoleset.add(adminRole);
//        String adminUUID = UUID.randomUUID().toString();
//
//        String userUUID = UUID.randomUUID().toString();
//        Role userRole=new Role(RoleEnum.USER);
//        Set<Role> userRoleset=new HashSet<>();
//        userRoleset.add(userRole);
////        String[] answer = new String[3];
////        answer[0] = "sdgsgd";
////        answer[1]= "desgt";
////        answer[2] = "etest";
//
////        UserAddress userAddress = new UserAddress();
////        userAddress.setCity("addis ababa");
////        userAddress.setStreet("str1");
//
//
//        NewUserDetail newUserDetail = new NewUserDetail();
//        newUserDetail.setUserPublicId(userUUID);
//        newUserDetail.setFirstName("alemayew");
//        newUserDetail.setLastName("tesma");
//        newUserDetail.setPhoneNumber("0987542136");
//        newUserDetail.setUserPassword("123456");
//        newUserDetail.setEmail("userUser@gmail.com");
////        newUserDetail.setAddress(userAddress);
////        newUserDetail.setQuestionsAndAnswers(answer);
//        newUserDetail.setVerifiedEmail("userUser@gmail.com");
//        newUserDetail.setIsEmailVerified(true);
//
//        NewUserDetail newAdminDetail = new NewUserDetail();
//        newAdminDetail.setUserPublicId(adminUUID);
//        newAdminDetail.setFirstName("Miftah");
//        newAdminDetail.setLastName("Kemal");
//        newAdminDetail.setPhoneNumber("4699871815");
//        newAdminDetail.setUserPassword("adminadmin");
//        newAdminDetail.setEmail("admin@gmail.com");
////        newAdminDetail.setAddress(userAddress);
////        newAdminDetail.setQuestionsAndAnswers(answer);
//        newAdminDetail.setVerifiedEmail("admin@gmail.com");
//        newAdminDetail.setIsEmailVerified(true);
//
//        NewUserDetail userDetail = userRegistrationService.saveUser(newUserDetail, userRoleset);
//        NewUserDetail adminDetail1 = userRegistrationService.saveUser(newAdminDetail, adminRoleset);
//
//
//        User loggedInUser= userRegistrationService.getUser(newUserDetail.getUserPublicId());
//        User loggedInAdmin= userRegistrationService.getUser(newAdminDetail.getUserPublicId());
//
//        List<Certificate>certificates = new ArrayList<>();
//
//        Certificate cer = new Certificate();
//        cer.setAwardYear(2012);
//        cer.setName("sgds");
//        cer.setRemark("12300");
//        certificates.add(cer);
//        Shop  shop = new Shop();
//        List<ShopImage> images2= new ArrayList<>();
//       ShopImage shopImageEntity = new ShopImage();
//       shopImageEntity.setUrl("https://cdn4.vectorstock.com/i/1000x1000/70/83/shop-store-icon-vector-30737083.jpg");
//       images2.add(shopImageEntity);
//       shop.setShopImages(images2);
//
//       WholeSellerDetail wholeSellerDetailEntity = new WholeSellerDetail();
//       wholeSellerDetailEntity.setNumberOfEmployee(2000);
//       wholeSellerDetailEntity.setProductionCapacity("sdsgags sgag");
//       wholeSellerDetailEntity.setTimeToDelivery("3 days");
//       wholeSellerDetailEntity.setCertificate(certificates);
//       shop.setWholeSellerDetail(wholeSellerDetailEntity);
//
//       Set<SocialMediaLink> socialMediaLinkList = new HashSet<>();
//       SocialMediaLink socialMediaLink = new SocialMediaLink();
//       socialMediaLink.setUrl("www.facebook.com/samrawit01");
//       socialMediaLink.setEnumSocialLinkType(SOCIAL_LINK_TYPE.FACE_BOOK);
//       socialMediaLinkList.add(socialMediaLink);
//       shop.setSocialMedia(socialMediaLinkList);
//
//       Set<OpenHour> opeHourList = new HashSet<>();
//       OpenHour openHour = new OpenHour();
//       openHour.setStartingHour("02:00");
//       openHour.setEndingHour("11:00");
//       openHour.setEnumDayOfWeek(DAY_OF_WEEK.MONDAY);
//       opeHourList.add(openHour);
//       OpenHour openHour2 = new OpenHour();
//       openHour2.setStartingHour("02:00");
//       openHour2.setEndingHour("11:00");
//       openHour2.setEnumDayOfWeek(DAY_OF_WEEK.TUESDAY);
//       opeHourList.add(openHour2);
//
//        shop.setOpenHours(opeHourList);
//
//
//        List<Address> addressList = new ArrayList<>();
//        Address address = new Address();
//        address.setCity("addis ababa");
//        address.setStreet("str1");
//        addressList.add(address);
//        shop.setAddress(addressList);
//        shop.setTag("shop");
//        shop.setSellerType("whole Seller");
//        shop.setCompanyRegistration("sgasgaeta");
//        shop.setDescription(" your shop is the best electronic shop in the country");
//        shop.setProfileImageUrl("https://img.favpng.com/25/13/19/samsung-galaxy-a8-a8-user-login-telephone-avatar-png-favpng-dqKEPfX7hPbc6SMVUCteANKwj.jpg");
//        shop.setLatitude(98.25);
//        shop.setLongitude(25.36);
//        shop.setName("BestElectronicShop");
//        shop.setPhoneNumber("01189756932");
//        shop.setOwner(loggedInAdmin);
//
//        Product product = new Product();
//        product.setName("Samsung A1");
//        product.setDescription("battery life 3 days, screen size 10inch");
//        product.setSubTitle("Samsung A1 Brand new ");
//        product.setPrice(6500);
//        product.setStock(20);
//        product.setBrand("Samsung");
//        product.setShopId(shop.getId());
//
//        List<ProductImages> productImagesList= new ArrayList<ProductImages>();
//        ProductImages productImagesEntity = new ProductImages();
//        productImagesEntity.setUrl("https://cdn4.vectorstock.com/i/1000x1000/70/83/shop-store-icon-vector-30737083.jpg");
//        productImagesList.add(productImagesEntity);
//        product.setProductImages(productImagesList);
//
//        Category category1 = new Category();
//        category1.setSubCategory("Mobile");
//        category1.setCategoryName("Electronic");
//        category1.setCategoryImage("https://image.shutterstock.com/image-illustration/collection-consumer-electronics-flying-air-260nw-738107467.jpg");
//        product.setProductCategory(category1);
//
////        Category category = new Category(1L,"Electronic","Mobile","https://image.shutterstock.com/image-illustration/collection-consumer-electronics-flying-air-260nw-738107467.jpg");
////        categoryService.saveCategory(category);
////        Category cat = categoryService.getCategory(category.getId());
////        product.setProductCategory(cat);
//
//        List<Option> options= new ArrayList<>();
//        Option size = new Option();
//        size.setOptionType("Size");
//        size.setOptionValue("S");
//        options.add(size);
//        Option color = new Option();
//        color.setOptionType("Color");
//        color.setOptionValue("red");
//        options.add(color);
//        product.setOptions(options);
//
//        ProductCriteria productCriteria = new ProductCriteria();
//        productCriteria.setRemark("you are not allowed to return product after 2 day from Purchase");
//        productCriteria.setMinRequirement("sgagdg");
//        product.setProductCriteria(productCriteria);
//
//        Shop shop1 = shopService.saveShop(shop);
//        Product  product1 =  productService.saveProduct(product);
//        favoritesService.addFavorites(product1.getId(), loggedInAdmin.getUserPublicId());
//
//        ProductFeedback newProductFeedback = new ProductFeedback();
//        newProductFeedback.setFeedback_type(FEEDBACK_TYPE.USER_FEEDBACK);
//        newProductFeedback.setProduct(product1);
//        newProductFeedback.setFeedback("This product is a good product");
//        newProductFeedback.setCurrentTime(LocalDateTime.now());
//        productFeedbackService.giveProductFeedback(newProductFeedback,product1.getId(),loggedInAdmin.getUserPublicId());
//
//        ShopFeedback newShopFeedback = new ShopFeedback();
//        newShopFeedback.setFeedback_type(FEEDBACK_TYPE.USER_FEEDBACK);
//        newShopFeedback.setShop(shop1);
//        newShopFeedback.setFeedback("This shop is a the best shop ever");
//        newShopFeedback.setCurrentTime(LocalDateTime.now());
//        shopFeedbackService.giveShopFeedback(newShopFeedback,product1.getId(),loggedInAdmin.getUserPublicId());
//
//
//        rateProductService.rateProduct(4.5,product1,loggedInAdmin.getUserPublicId());
//
//        rateShopService.rateShop(4.5,shop1,loggedInAdmin.getUserPublicId());
//
//        ProductQuestionAndAnswer productQuestionAndAnswer = new ProductQuestionAndAnswer();
//        productQuestionAndAnswer.setQuestion("how is this product packed");
//        ProductQuestionAndAnswer productQuestionAndAnswer1 =   productQuestionAndAnswerService.saveQuestion(productQuestionAndAnswer,product1.getId(),loggedInAdmin.getUserPublicId());
//
//        ProductQuestionAndAnswer answer =productQuestionAndAnswerService.getProductQueToAnswerId(productQuestionAndAnswer1.getId());
//        answer.setAnswer("It is packed 2 set per ctn and one set have 20 pcs");
//        productQuestionAndAnswerService.saveAnswer(answer,loggedInAdmin.getUserPublicId());
//
//        ShopQuestionAndAnswer shopQuestionAndAnswer = new ShopQuestionAndAnswer();
//        shopQuestionAndAnswer.setQuestion("do you have a mobile A5");
//        ShopQuestionAndAnswer shopQuestionAndAnswer1 =   shopQuestionAndAnswerService.saveQuestion(shopQuestionAndAnswer,shop1.getId(),loggedInAdmin.getUserPublicId());
//
//        ShopQuestionAndAnswer shopAnswer =shopQuestionAndAnswerService.getShopQueToAnswerId(shopQuestionAndAnswer1.getId());
//        shopAnswer.setAnswer("Yes we have");
//        shopQuestionAndAnswerService.saveAnswer(shopAnswer,loggedInAdmin.getUserPublicId());
//
//
////
//////////        CarPriceType fixed = new CarPriceType();
//////////        fixed.setName("Fixed");
//////////        priceTypeRepo.save(fixed);
//////////        CarPriceType negotiable = new CarPriceType();
//////////        negotiable.setName("Negotiable");
//////////        priceTypeRepo.save(negotiable);
//////////
//////////
//////////        CarType carType = new CarType();
//////////        carType.setName("SUV");
//////////        carRegistrationService.saveCarType(carType);
//////////
//////////
//////////        CarType carType2 = new CarType();
//////////        carType2.setName("Sedan");
//////////        carRegistrationService.saveCarType(carType2);
//////////
//////////        CarType carType3 = new CarType();
//////////        carType3.setName("Pickup");
//////////        carRegistrationService.saveCarType(carType3);
//////////
//////////        User loggedInUser= userRegistrationService.getUser(newUserDetail.getUserPublicId());
//////////        User loggedInAdmin= userRegistrationService.getUser(newAdminDetail.getUserPublicId());
//////////
//////////        CarModel car1=new CarModel();
//////////        car1.setCarType("SUV");
//////////        car1.setCondition("Brand New/አዲስ");
//////////        car1.setMake("Acura");
//////////        car1.setMileage(33);
//////////        car1.setModel("CL");
//////////        car1.setPrice(3333.33);
//////////        car1.setPriceType("Fixed");
//////////        car1.setYear(2020);
//////////        car1.setColor("Deeppink");
//////////        car1.setRegisteredTime((new Date()).getTime());
//////////        car1.setSold(false);
//////////        car1.setDescription("We Deliver! Please consider inquiring about our vehicle pick-up and delivery services. Our online digital storefront will continue to be a helpful resource for shopping inventory, vehicle research, service and parts information, and communicating directly with the dealership. No matter the department you're looking to reach, we have staff that can assist your needs.");
//////////
//////////        car1.setPostedByPublicID(loggedInUser.getUserPublicId());
//////////
//////////        Set<CarImageEntity> images1= new HashSet<>();
//////////        CarImageEntity carImageEntity = new CarImageEntity();
//////////        carImageEntity.setUrl("https://cdn-w.v12soft.com/photos/NiMu71d/12670726/001_310120200757268788_800600.jpg");
//////////        images1.add(carImageEntity);
//////////        car1.setCarImages(images1);
//////////        carRegistrationService.registerCar(car1);
//////////
//////////        CarModel car2=new CarModel();
//////////        car2.setCarType("SUV");
//////////        car2.setCondition("Brand New/አዲስ");
//////////        car2.setMake("Acura");
//////////        car2.setMileage(44);
//////////        car2.setModel("CL");
//////////        car2.setPrice(4444.33);
//////////        car2.setPriceType("Fixed");
//////////        car2.setYear(2019);
//////////        car2.setColor("Deeppink");
//////////        car2.setRegisteredTime((new Date()).getTime());
//////////        car2.setSold(false);
//////////
//////////
//////////        car2.setPostedByPublicID(loggedInAdmin.getUserPublicId());
//////////
//////////        Set<CarImageEntity> images2= new HashSet<>();
//////////        CarImageEntity carImageEntity2 = new CarImageEntity();
//////////        carImageEntity2.setUrl("https://www.gtopcars.com/wp-content/uploads/2020/05/2020-Infiniti-g35.jpg");
//////////        images2.add(carImageEntity2);
//////////        car1.setCarImages(images2);
//////////
//////////
//////////        carRegistrationService.registerCar(car2);
////////////        {
////////////            "id": 10,
////////////                "carType": "SUV",
////////////                "condition": "Brand New/አዲስ",
////////////                "make": "Acura",
////////////                "mileage": 33,
////////////                "model": "CL",
////////////                "price": 3333.0,
////////////                "priceType": "Fixed",
////////////                "year": 2020,
////////////                "color": "Deeppink",
////////////                "carImages": [],
////////////            "timeStamp": 1604524903194,
////////////                "sold": false
////////////        }
//   }
//}
