package com.mbn.elkhodary.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Home implements Serializable {

    @SerializedName("products_carousel")
    @Expose
    public ProductsCarousel     productsCarousel;
    @SerializedName("app_logo_light")
    @Expose
    public String               appLogoLight;
    @SerializedName("app_logo")
    @Expose
    public String               appLogo;
    @SerializedName("main_category")
    @Expose
    public List<MainCategory>   mainCategory    = null;
    @SerializedName("main_slider")
    @Expose
    public List<MainSlider>     mainSlider      = null;
    @SerializedName("category_banners")
    @Expose
    public List<CategoryBanner> categoryBanners = null;
    @SerializedName("banner_ad")
    @Expose
    public List<BannerAd>       bannerAd        = null;
    @SerializedName("static_page")
    @Expose
    public StaticPage           staticPage;
    @SerializedName("info_pages")
    @Expose
    public List<InfoPage>       infoPages       = null;
    @SerializedName("pgs_app_social_links")
    @Expose
    public PgsAppSocialLinks    pgsAppSocialLinks;
    @SerializedName("feature_box_status")
    @Expose
    public String               featureBoxStatus;
    @SerializedName("feature_box_heading")
    @Expose
    public String               featureBoxHeading;
    @SerializedName("feature_box")
    @Expose
    public List<FeatureBox>     featureBox      = null;
    @SerializedName("pgs_app_contact_info")
    @Expose
    public PgsAppContactInfo    pgsAppContactInfo;

    @SerializedName("product_banners_cat_value")
    @Expose
    public String product_banners_cat_value;

    @SerializedName("product_banners_title")
    @Expose
    public String              product_banners_title;
    @SerializedName("all_categories")
    @Expose
    public List<AllCategory>   allCategories   = null;
    @SerializedName("is_wishlist_active")
    @Expose
    public Boolean             isWishlistActive;
    @SerializedName("is_yith_featured_video_active")
    @Expose
    public Boolean             isYithFeaturedVideoActive;
    @SerializedName("is_currency_switcher_active")
    @Expose
    public Boolean             isCurrencySwitcherActive;
    @SerializedName("is_order_tracking_active")
    @Expose
    public Boolean             isOrderTrackingActive;
    @SerializedName("is_reward_points_active")
    @Expose
    public Boolean             isRewardPointsActive;
    @SerializedName("is_abandoned_cart_active")
    @Expose
    public Boolean             isAbandonedCartActive;
    @SerializedName("is_guest_checkout_active")
    @Expose
    public Boolean             isGuestCheckoutActive;
    @SerializedName("is_add_to_cart_btn_active")
    @Expose
    public Boolean             isAddToCartBtnActive;
    @SerializedName("is_wpml_active")
    @Expose
    public Boolean             isWpmlActive;
    @SerializedName("price_formate_options")
    @Expose
    public PriceFormateOptions priceFormateOptions;
    @SerializedName("ios_app_url")
    @Expose
    public String              iosAppUrl;
    @SerializedName("site_language")
    @Expose
    public String              siteLanguage;
    @SerializedName("app_color")
    @Expose
    public AppColor            appColor;
    @SerializedName("is_rtl")
    @Expose
    public Boolean             isRtl;
    @SerializedName("clockIcon")
    @Expose
    public String              clockIcon;
    @SerializedName("notificationIcon")
    @Expose
    public String              notificationIcon;
    @SerializedName("popular_products")
    @Expose
    public List<Product>       popularProducts = null;

    @SerializedName("custom_section")
    @Expose
    public List<Product> custom_section = null;

    @SerializedName("products_view_orders")
    @Expose
    public List<ProductsViewOrder> productsViewOrders = null;

    public MainCategory mainCategoryObject;

    @SerializedName("scheduled_sale_products")
    @Expose
    public ScheduledSaleProducts scheduledSaleProducts;

    @SerializedName("wpml_languages")
    @Expose
    public List<WpmlLanguage> wpmlLanguages = null;

    @SerializedName("pgs_woo_api_web_view_pages")
    @Expose
    public List<WebViewPages> webViewPages = null;

    @SerializedName("checkout_redirect_url")
    @Expose
    public List<String> checkoutRedirectUrl;

    @SerializedName("checkout_cancel_url")
    @Expose
    public List<String> checkoutCancelUrl;

    @SerializedName("is_app_validation")
    @Expose
    public Boolean isAppValidation;

    @SerializedName("pgs_woo_api_deliver_pincode")
    @Expose
    public PgsWooApiDeliverPincode pgsWooApiDeliverPincode;


    public Home withScheduledSaleProducts(ScheduledSaleProducts scheduledSaleProducts) {
        this.scheduledSaleProducts = scheduledSaleProducts;
        return this;
    }

    public MainCategory getInstranceMainCategory() {
        mainCategoryObject = new MainCategory();
        return mainCategoryObject;
    }

    public Home withProductsCarousel(ProductsCarousel productsCarousel) {
        this.productsCarousel = productsCarousel;
        return this;
    }

    public Home withAppLogoLight(String appLogoLight) {
        this.appLogoLight = appLogoLight;
        return this;
    }

    public Home withAppLogo(String appLogo) {
        this.appLogo = appLogo;
        return this;
    }

    public Home withMainCategory(List<MainCategory> mainCategory) {
        this.mainCategory = mainCategory;
        return this;
    }

    public Home withMainSlider(List<MainSlider> mainSlider) {
        this.mainSlider = mainSlider;
        return this;
    }

    public Home withCategoryBanners(List<CategoryBanner> categoryBanners) {
        this.categoryBanners = categoryBanners;
        return this;
    }

    public Home withBannerAd(List<BannerAd> bannerAd) {
        this.bannerAd = bannerAd;
        return this;
    }

    public Home withStaticPage(StaticPage staticPage) {
        this.staticPage = staticPage;
        return this;
    }

    public Home withInfoPages(List<InfoPage> infoPages) {
        this.infoPages = infoPages;
        return this;
    }

    public Home withPgsAppSocialLinks(PgsAppSocialLinks pgsAppSocialLinks) {
        this.pgsAppSocialLinks = pgsAppSocialLinks;
        return this;
    }

    public Home withFeatureBoxStatus(String featureBoxStatus) {
        this.featureBoxStatus = featureBoxStatus;
        return this;
    }

    public Home withFeatureBoxHeading(String featureBoxHeading) {
        this.featureBoxHeading = featureBoxHeading;
        return this;
    }

    public Home withFeatureBox(List<FeatureBox> featureBox) {
        this.featureBox = featureBox;
        return this;
    }

    public Home withPgsAppContactInfo(PgsAppContactInfo pgsAppContactInfo) {
        this.pgsAppContactInfo = pgsAppContactInfo;
        return this;
    }

    public Home withAllCategories(List<AllCategory> allCategories) {
        this.allCategories = allCategories;
        return this;
    }

    public Home withIsWishlistActive(Boolean isWishlistActive) {
        this.isWishlistActive = isWishlistActive;
        return this;
    }

    public Home withIsCurrencySwitcherActive(Boolean isCurrencySwitcherActive) {
        this.isCurrencySwitcherActive = isCurrencySwitcherActive;
        return this;
    }

    public Home withIsOrderTrackingActive(Boolean isOrderTrackingActive) {
        this.isOrderTrackingActive = isOrderTrackingActive;
        return this;
    }

    public Home withIsRewardPointsActive(Boolean isRewardPointsActive) {
        this.isRewardPointsActive = isRewardPointsActive;
        return this;
    }

    public Home withIsAbandonedCartActive(Boolean isAbandonedCartActive) {
        this.isAbandonedCartActive = isAbandonedCartActive;
        return this;
    }

    public Home withIsGuestCheckoutActive(Boolean isGuestCheckoutActive) {
        this.isGuestCheckoutActive = isGuestCheckoutActive;
        return this;
    }

    public Home withIsWpmlActive(Boolean isWpmlActive) {
        this.isWpmlActive = isWpmlActive;
        return this;
    }

    public Home withPriceFormateOptions(PriceFormateOptions priceFormateOptions) {
        this.priceFormateOptions = priceFormateOptions;
        return this;
    }

    public Home withIosAppUrl(String iosAppUrl) {
        this.iosAppUrl = iosAppUrl;
        return this;
    }

    public Home withSiteLanguage(String siteLanguage) {
        this.siteLanguage = siteLanguage;
        return this;
    }

    public Home withAppColor(AppColor appColor) {
        this.appColor = appColor;
        return this;
    }

    public Home withIsRtl(Boolean isRtl) {
        this.isRtl = isRtl;
        return this;
    }

    public SettingOptions getSettingOption() {
        return new SettingOptions();
    }

    public class PgsWooApiDeliverPincode {

        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("setting_options")
        @Expose
        public SettingOptions settingOptions;

        public PgsWooApiDeliverPincode withStatus(String status) {
            this.status = status;
            return this;
        }

        public PgsWooApiDeliverPincode withSettingOptions(SettingOptions settingOptions) {
            this.settingOptions = settingOptions;
            return this;
        }

    }


    public class SettingOptions {

        @SerializedName("del_help_text")
        @Expose
        public String delHelpText;
        @SerializedName("cod_help_text")
        @Expose
        public String codHelpText;
        @SerializedName("cod_available_msg")
        @Expose
        public String codAvailableMsg;
        @SerializedName("cod_not_available_msg")
        @Expose
        public String codNotAvailableMsg;
        @SerializedName("error_msg_check_pincode")
        @Expose
        public String errorMsgCheckPincode;
        @SerializedName("del_saturday")
        @Expose
        public String delSaturday;
        @SerializedName("del_sunday")
        @Expose
        public String delSunday;
        @SerializedName("show_product_page")
        @Expose
        public String showProductPage;
        @SerializedName("del_data_label")
        @Expose
        public String delDataLabel;
        @SerializedName("cod_data_label")
        @Expose
        public String codDataLabel;
        @SerializedName("availableat_text")
        @Expose
        public String availableatText;
        @SerializedName("error_msg_blank")
        @Expose
        public String errorMsgBlank;
        @SerializedName("pincode_placeholder_txt")
        @Expose
        public String pincodePlaceholderTxt;
        @SerializedName("show_state_on_product")
        @Expose
        public String showStateOnProduct;
        @SerializedName("show_city_on_product")
        @Expose
        public String showCityOnProduct;
        @SerializedName("show_estimate_on_product")
        @Expose
        public boolean showEstimateOnProduct;
        @SerializedName("show_cod_on_product")
        @Expose
        public String showCodOnProduct;

        public SettingOptions withDelHelpText(String delHelpText) {
            this.delHelpText = delHelpText;
            return this;
        }

        public SettingOptions withCodHelpText(String codHelpText) {
            this.codHelpText = codHelpText;
            return this;
        }

        public SettingOptions withCodAvailableMsg(String codAvailableMsg) {
            this.codAvailableMsg = codAvailableMsg;
            return this;
        }

        public SettingOptions withCodNotAvailableMsg(String codNotAvailableMsg) {
            this.codNotAvailableMsg = codNotAvailableMsg;
            return this;
        }

        public SettingOptions withErrorMsgCheckPincode(String errorMsgCheckPincode) {
            this.errorMsgCheckPincode = errorMsgCheckPincode;
            return this;
        }

        public SettingOptions withDelSaturday(String delSaturday) {
            this.delSaturday = delSaturday;
            return this;
        }

        public SettingOptions withDelSunday(String delSunday) {
            this.delSunday = delSunday;
            return this;
        }

        public SettingOptions withShowProductPage(String showProductPage) {
            this.showProductPage = showProductPage;
            return this;
        }

        public SettingOptions withDelDataLabel(String delDataLabel) {
            this.delDataLabel = delDataLabel;
            return this;
        }

        public SettingOptions withCodDataLabel(String codDataLabel) {
            this.codDataLabel = codDataLabel;
            return this;
        }

        public SettingOptions withAvailableatText(String availableatText) {
            this.availableatText = availableatText;
            return this;
        }

        public SettingOptions withErrorMsgBlank(String errorMsgBlank) {
            this.errorMsgBlank = errorMsgBlank;
            return this;
        }

        public SettingOptions withPincodePlaceholderTxt(String pincodePlaceholderTxt) {
            this.pincodePlaceholderTxt = pincodePlaceholderTxt;
            return this;
        }

        public SettingOptions withShowStateOnProduct(String showStateOnProduct) {
            this.showStateOnProduct = showStateOnProduct;
            return this;
        }

        public SettingOptions withShowCityOnProduct(String showCityOnProduct) {
            this.showCityOnProduct = showCityOnProduct;
            return this;
        }

        public SettingOptions withShowEstimateOnProduct(boolean showEstimateOnProduct) {
            this.showEstimateOnProduct = showEstimateOnProduct;
            return this;
        }

        public SettingOptions withShowCodOnProduct(String showCodOnProduct) {
            this.showCodOnProduct = showCodOnProduct;
            return this;
        }

    }

    public class ScheduledSaleProducts {

        @SerializedName("status")
        @Expose
        public String        status;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public ScheduledSaleProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public ScheduledSaleProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }

    public class DealLife {

        @SerializedName("hours")
        @Expose
        public String hours;
        @SerializedName("minutes")
        @Expose
        public String minutes;
        @SerializedName("seconds")
        @Expose
        public String seconds;

        public DealLife withHours(String hours) {
            this.hours = hours;
            return this;
        }

        public DealLife withMinutes(String minutes) {
            this.minutes = minutes;
            return this;
        }

        public DealLife withSeconds(String seconds) {
            this.seconds = seconds;
            return this;
        }

    }

   /* public class PopularProduct {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("price_html")
        @Expose
        public String priceHtml;
        @SerializedName("price")
        @Expose
        public Price price;
        @SerializedName("rating")
        @Expose
        public String rating;
        @SerializedName("in_stock")
        @Expose
        public Boolean inStock;
        @SerializedName("manage_stock")
        @Expose
        public Boolean manageStock;
        @SerializedName("stock_quantity")
        @Expose
        public String stock_quantity;
        @SerializedName("app_thumbnail")
        @Expose
        public String app_thumbnail;

        public PopularProduct withId(String id) {
            this.id = id;
            return this;
        }

        public PopularProduct withstock_quantity(String stock_quantity) {
            this.stock_quantity = stock_quantity;
            return this;
        }

        public PopularProduct withapp_thumbnail(String app_thumbnail) {
            this.app_thumbnail = app_thumbnail;
            return this;
        }

        public PopularProduct withmanageStock(Boolean manageStock) {
            this.manageStock = manageStock;
            return this;
        }

        public PopularProduct withinStock(Boolean inStock) {
            this.inStock = inStock;
            return this;
        }

        public PopularProduct withTitle(String title) {
            this.title = title;
            return this;
        }

        public PopularProduct withImage(String image) {
            this.image = image;
            return this;
        }

        public PopularProduct withPriceHtml(String priceHtml) {
            this.priceHtml = priceHtml;
            return this;
        }

        public PopularProduct withPrice(Price price) {
            this.price = price;
            return this;
        }

        public PopularProduct withRating(String rating) {
            this.rating = rating;
            return this;
        }

    }*/

    public class AllCategory {

        @SerializedName("description")
        @Expose
        public String             description;
        @SerializedName("id")
        @Expose
        public Integer            id;
        @SerializedName("image")
        @Expose
        public CategoryList.Image image;
        @SerializedName("name")
        @Expose
        public String             name;
        @SerializedName("parent")
        @Expose
        public Integer            parent;
        @SerializedName("slug")
        @Expose
        public String             slug;

        public AllCategory withDescription(String description) {
            this.description = description;
            return this;
        }

        public AllCategory withId(Integer id) {
            this.id = id;
            return this;
        }

        public AllCategory withImage(CategoryList.Image image) {
            this.image = image;
            return this;
        }

        public AllCategory withName(String name) {
            this.name = name;
            return this;
        }

        public AllCategory withParent(Integer parent) {
            this.parent = parent;
            return this;
        }

        public AllCategory withSlug(String slug) {
            this.slug = slug;
            return this;
        }

    }


    public class AppColor {

        @SerializedName("header_color")
        @Expose
        public String headerColor;
        @SerializedName("primary_color")
        @Expose
        public String primaryColor;
        @SerializedName("secondary_color")
        @Expose
        public String secondaryColor;

        public AppColor withHeaderColor(String headerColor) {
            this.headerColor = headerColor;
            return this;
        }

        public AppColor withPrimaryColor(String primaryColor) {
            this.primaryColor = primaryColor;
            return this;
        }

        public AppColor withSecondaryColor(String secondaryColor) {
            this.secondaryColor = secondaryColor;
            return this;
        }

    }


    public class BannerAd {

        @SerializedName("banner_ad_image_url")
        @Expose
        public String bannerAdImageUrl;
        @SerializedName("banner_ad_image_id")
        @Expose
        public String bannerAdImageId;
        @SerializedName("banner_ad_cat_id")
        @Expose
        public String bannerAdCatId;

        public BannerAd withBannerAdImageUrl(String bannerAdImageUrl) {
            this.bannerAdImageUrl = bannerAdImageUrl;
            return this;
        }

        public BannerAd withBannerAdImageId(String bannerAdImageId) {
            this.bannerAdImageId = bannerAdImageId;
            return this;
        }

        public BannerAd withBannerAdCatId(String bannerAdCatId) {
            this.bannerAdCatId = bannerAdCatId;
            return this;
        }

    }


    public class CategoryBanner {

        @SerializedName("cat_banners_image_id")
        @Expose
        public String catBannersImageId;
        @SerializedName("cat_banners_image_url")
        @Expose
        public String catBannersImageUrl;
        @SerializedName("cat_banners_cat_id")
        @Expose
        public String catBannersCatId;
        @SerializedName("cat_banners_title")
        @Expose
        public String catBannersTitle;

        public CategoryBanner withCatBannersImageId(String catBannersImageId) {
            this.catBannersImageId = catBannersImageId;
            return this;
        }

        public CategoryBanner withCatBannersImageUrl(String catBannersImageUrl) {
            this.catBannersImageUrl = catBannersImageUrl;
            return this;
        }

        public CategoryBanner withCatBannersCatId(String catBannersCatId) {
            this.catBannersCatId = catBannersCatId;
            return this;
        }

        public CategoryBanner withCatBannersTitle(String catBannersTitle) {
            this.catBannersTitle = catBannersTitle;
            return this;
        }
    }

    public class FeatureBox {

        @SerializedName("feature_image_id")
        @Expose
        public String featureImageId;
        @SerializedName("feature_title")
        @Expose
        public String featureTitle;
        @SerializedName("feature_content")
        @Expose
        public String featureContent;
        @SerializedName("feature_image")
        @Expose
        public String featureImage;

        public FeatureBox withFeatureImageId(String featureImageId) {
            this.featureImageId = featureImageId;
            return this;
        }

        public FeatureBox withFeatureTitle(String featureTitle) {
            this.featureTitle = featureTitle;
            return this;
        }

        public FeatureBox withFeatureContent(String featureContent) {
            this.featureContent = featureContent;
            return this;
        }

        public FeatureBox withFeatureImage(String featureImage) {
            this.featureImage = featureImage;
            return this;
        }

    }

    public class FeatureProducts {

        @SerializedName("status")
        @Expose
        public String        status;
        @SerializedName("title")
        @Expose
        public String        title;
        @SerializedName("orderby")
        @Expose
        public String        orderby;
        @SerializedName("order")
        @Expose
        public String        order;
        @SerializedName("screen_order")
        @Expose
        public Integer       screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public FeatureProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public FeatureProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public FeatureProducts withOrderby(String orderby) {
            this.orderby = orderby;
            return this;
        }

        public FeatureProducts withOrder(String order) {
            this.order = order;
            return this;
        }

        public FeatureProducts withScreenOrder(Integer screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public FeatureProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }

    public class InfoPage {

        @SerializedName("info_pages_page_id")
        @Expose
        public String infoPagesPageId;

        public InfoPage withInfoPagesPageId(String infoPagesPageId) {
            this.infoPagesPageId = infoPagesPageId;
            return this;
        }

    }


    public class MainCategory {

        @SerializedName("main_cat_id")
        @Expose
        public String mainCatId;
        @SerializedName("main_cat_name")
        @Expose
        public String mainCatName;
        @SerializedName("main_cat_image")
        @Expose
        public String mainCatImage;

        public MainCategory withMainCatId(String mainCatId) {
            this.mainCatId = mainCatId;
            return this;
        }

        public MainCategory withMainCatName(String mainCatName) {
            this.mainCatName = mainCatName;
            return this;
        }

        public MainCategory withMainCatImage(String mainCatImage) {
            this.mainCatImage = mainCatImage;
            return this;
        }

    }


    public class MainSlider {

        @SerializedName("upload_image_id")
        @Expose
        public String uploadImageId;
        @SerializedName("slider_cat_id")
        @Expose
        public String sliderCatId;
        @SerializedName("upload_image_url")
        @Expose
        public String uploadImageUrl;

        public MainSlider withUploadImageId(String uploadImageId) {
            this.uploadImageId = uploadImageId;
            return this;
        }

        public MainSlider withSliderCatId(String sliderCatId) {
            this.sliderCatId = sliderCatId;
            return this;
        }

        public MainSlider withUploadImageUrl(String uploadImageUrl) {
            this.uploadImageUrl = uploadImageUrl;
            return this;
        }

    }


    public class PgsAppContactInfo {

        @SerializedName("address_line_1")
        @Expose
        public String addressLine1;
        @SerializedName("address_line_2")
        @Expose
        public String addressLine2;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("phone")
        @Expose
        public String phone;
        @SerializedName("whatsapp_no")
        @Expose
        public String whatsappNo;
        @SerializedName("whatsapp_floating_button")
        @Expose
        public String whatsappFloatingButton;

        public PgsAppContactInfo withAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public PgsAppContactInfo withAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public PgsAppContactInfo withEmail(String email) {
            this.email = email;
            return this;
        }

        public PgsAppContactInfo withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public PgsAppContactInfo withWhatsappNo(String whatsappNo) {
            this.whatsappNo = whatsappNo;
            return this;
        }

        public PgsAppContactInfo withwhatsappFloatingButton(String whatsappFloatingButton) {
            this.whatsappFloatingButton = whatsappFloatingButton;
            return this;
        }

    }


    public class PgsAppSocialLinks {

        @SerializedName("facebook")
        @Expose
        public String facebook;
        @SerializedName("twitter")
        @Expose
        public String twitter;
        @SerializedName("linkedin")
        @Expose
        public String linkedin;
        @SerializedName("google_plus")
        @Expose
        public String googlePlus;
        @SerializedName("pinterest")
        @Expose
        public String pinterest;
        @SerializedName("instagram")
        @Expose
        public String instagram;

        public PgsAppSocialLinks withFacebook(String facebook) {
            this.facebook = facebook;
            return this;
        }

        public PgsAppSocialLinks withTwitter(String twitter) {
            this.twitter = twitter;
            return this;
        }

        public PgsAppSocialLinks withLinkedin(String linkedin) {
            this.linkedin = linkedin;
            return this;
        }

        public PgsAppSocialLinks withGooglePlus(String googlePlus) {
            this.googlePlus = googlePlus;
            return this;
        }

        public PgsAppSocialLinks withPinterest(String pinterest) {
            this.pinterest = pinterest;
            return this;
        }

        public PgsAppSocialLinks withInstagram(String instagram) {
            this.instagram = instagram;
            return this;
        }

    }


    public class PopularProducts {

        @SerializedName("status")
        @Expose
        public String        status;
        @SerializedName("title")
        @Expose
        public String        title;
        @SerializedName("orderby")
        @Expose
        public String        orderby;
        @SerializedName("order")
        @Expose
        public String        order;
        @SerializedName("screen_order")
        @Expose
        public Integer       screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public PopularProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public PopularProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public PopularProducts withOrderby(String orderby) {
            this.orderby = orderby;
            return this;
        }

        public PopularProducts withOrder(String order) {
            this.order = order;
            return this;
        }

        public PopularProducts withScreenOrder(Integer screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public PopularProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }


    public class Price {

        @SerializedName("regular_price")
        @Expose
        public String regularPrice;
        @SerializedName("sale_price")
        @Expose
        public String salePrice;
        @SerializedName("price")
        @Expose
        public String price;
        @SerializedName("tax_price")
        @Expose
        public String taxPrice;
        @SerializedName("tax_status")
        @Expose
        public String taxStatus;
        @SerializedName("tax_class")
        @Expose
        public String taxClass;

        public Price withRegularPrice(String regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }

        public Price withSalePrice(String salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Price withPrice(String price) {
            this.price = price;
            return this;
        }

        public Price withTaxPrice(String taxPrice) {
            this.taxPrice = taxPrice;
            return this;
        }

        public Price withTaxStatus(String taxStatus) {
            this.taxStatus = taxStatus;
            return this;
        }

        public Price withTaxClass(String taxClass) {
            this.taxClass = taxClass;
            return this;
        }

    }


    public class PriceFormateOptions {

        @SerializedName("decimal_separator")
        @Expose
        public String  decimalSeparator;
        @SerializedName("thousand_separator")
        @Expose
        public String  thousandSeparator;
        @SerializedName("decimals")
        @Expose
        public Integer decimals;
        @SerializedName("currency_pos")
        @Expose
        public String  currencyPos;
        @SerializedName("currency_symbol")
        @Expose
        public String  currencySymbol;
        @SerializedName("currency_code")
        @Expose
        public String  currencyCode;

        public PriceFormateOptions withDecimalSeparator(String decimalSeparator) {
            this.decimalSeparator = decimalSeparator;
            return this;
        }

        public PriceFormateOptions withThousandSeparator(String thousandSeparator) {
            this.thousandSeparator = thousandSeparator;
            return this;
        }

        public PriceFormateOptions withDecimals(Integer decimals) {
            this.decimals = decimals;
            return this;
        }

        public PriceFormateOptions withCurrencyPos(String currencyPos) {
            this.currencyPos = currencyPos;
            return this;
        }

        public PriceFormateOptions withCurrencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
            return this;
        }

        public PriceFormateOptions withCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
            return this;
        }

    }


    public class Product {

        @SerializedName("title")
        @Expose
        public String  title;
        @SerializedName("image")
        @Expose
        public String  image;
        @SerializedName("price_html")
        @Expose
        public String  priceHtml;
        @SerializedName("price")
        @Expose
        public String  price;
        @SerializedName("rating")
        @Expose
        public String  rating;
        @SerializedName("app_thumbnail")
        @Expose
        public String  appThumbnail;
        @SerializedName("id")
        @Expose
        public String  id;
        @SerializedName("name")
        @Expose
        public String  name;
        @SerializedName("slug")
        @Expose
        public String  slug;
        @SerializedName("permalink")
        @Expose
        public String  permalink;
        @SerializedName("date_created")
        @Expose
        public String  dateCreated;
        @SerializedName("date_created_gmt")
        @Expose
        public String  dateCreatedGmt;
        @SerializedName("date_modified")
        @Expose
        public String  dateModified;
        @SerializedName("date_modified_gmt")
        @Expose
        public String  dateModifiedGmt;
        @SerializedName("type")
        @Expose
        public String  type;
        @SerializedName("status")
        @Expose
        public String  status;
        @SerializedName("featured")
        @Expose
        public boolean featured;
        @SerializedName("catalog_visibility")
        @Expose
        public String  catalogVisibility;
        @SerializedName("description")
        @Expose
        public String  description;
        @SerializedName("short_description")
        @Expose
        public String  shortDescription;
        @SerializedName("sku")
        @Expose
        public String  sku;
        @SerializedName("tax_price")
        @Expose
        public String  taxPrice;
        @SerializedName("price_excluding_tax")
        @Expose
        public String  priceExcludingTax;
        @SerializedName("price_including_tax")
        @Expose
        public String  priceIncludingTax;
        @SerializedName("regular_price")
        @Expose
        public String  regularPrice;
        @SerializedName("sale_price")
        @Expose
        public String  salePrice;
        @SerializedName("date_on_sale_from")
        @Expose
        public Object  dateOnSaleFrom;
        @SerializedName("date_on_sale_from_gmt")
        @Expose
        public Object  dateOnSaleFromGmt;
        @SerializedName("date_on_sale_to")
        @Expose
        public Object  dateOnSaleTo;
        @SerializedName("date_on_sale_to_gmt")
        @Expose
        public Object  dateOnSaleToGmt;
        @SerializedName("on_sale")
        @Expose
        public boolean onSale;
        @SerializedName("purchasable")
        @Expose
        public boolean purchasable;
        @SerializedName("total_sales")
        @Expose
        public String  totalSales;
        @SerializedName("virtual")
        @Expose
        public boolean virtual;
        @SerializedName("downloadable")
        @Expose
        public boolean downloadable;

        @SerializedName("download_limit")
        @Expose
        public int                          downloadLimit;
        @SerializedName("download_expiry")
        @Expose
        public int                          downloadExpiry;
        @SerializedName("external_url")
        @Expose
        public String                       externalUrl;
        @SerializedName("button_text")
        @Expose
        public String                       buttonText;
        @SerializedName("tax_status")
        @Expose
        public String                       taxStatus;
        @SerializedName("tax_class")
        @Expose
        public String                       taxClass;
        @SerializedName("manage_stock")
        @Expose
        public boolean                      manageStock;
        @SerializedName("stock_quantity")
        @Expose
        public Object                       stockQuantity;
        @SerializedName("in_stock")
        @Expose
        public boolean                      inStock;
        @SerializedName("backorders")
        @Expose
        public String                       backorders;
        @SerializedName("backorders_allowed")
        @Expose
        public boolean                      backordersAllowed;
        @SerializedName("backordered")
        @Expose
        public boolean                      backordered;
        @SerializedName("sold_individually")
        @Expose
        public boolean                      soldIndividually;
        @SerializedName("weight")
        @Expose
        public String                       weight;
        @SerializedName("dimensions")
        @Expose
        public Dimensions                   dimensions;
        @SerializedName("shipping_required")
        @Expose
        public boolean                      shippingRequired;
        @SerializedName("shipping_taxable")
        @Expose
        public boolean                      shippingTaxable;
        @SerializedName("shipping_class")
        @Expose
        public String                       shippingClass;
        @SerializedName("shipping_class_id")
        @Expose
        public int                          shippingClassId;
        @SerializedName("reviews_allowed")
        @Expose
        public boolean                      reviewsAllowed;
        @SerializedName("average_rating")
        @Expose
        public String                       averageRating;
        @SerializedName("rating_count")
        @Expose
        public int                          ratingCount;
        @SerializedName("related_ids")
        @Expose
        public List<Integer>                relatedIds        = null;
        @SerializedName("upsell_ids")
        @Expose
        public List<Object>                 upsellIds         = null;
        @SerializedName("cross_sell_ids")
        @Expose
        public List<Object>                 crossSellIds      = null;
        @SerializedName("parent_id")
        @Expose
        public int                          parentId;
        @SerializedName("purchase_note")
        @Expose
        public String                       purchaseNote;
        @SerializedName("categories")
        @Expose
        public List<Category>               categories        = null;
        @SerializedName("tags")
        @Expose
        public List<Object>                 tags              = null;
        @SerializedName("images")
        @Expose
        public List<CategoryList.Image>     images            = null;
        @SerializedName("attributes")
        @Expose
        public List<CategoryList.Attribute> attributes        = null;
        @SerializedName("default_attributes")
        @Expose
        public List<Object>                 defaultAttributes = null;
        @SerializedName("variations")
        @Expose
        public List<Object>                 variations        = null;
        @SerializedName("grouped_products")
        @Expose
        public List<Object>                 groupedProducts   = null;
        @SerializedName("menu_order")
        @Expose
        public int                          menuOrder;
        /* @SerializedName("meta_data")
         @Expose
         public List<MetaDatum> metaData = null;*/
        @SerializedName("rewards_message")
        @Expose
        public String                       rewardsMessage;
        @SerializedName("addition_info_html")
        @Expose
        public String                       additionInfoHtml;
        @SerializedName("featured_video")
        @Expose
        public FeaturedVideo                featuredVideo;
        @SerializedName("percentage")
        @Expose
        public int                          percentage;
        @SerializedName("deal_life")
        @Expose
        public DealLife                     dealLife;

        @SerializedName("seller_info")
        @Expose
        public CategoryList.SellerInfo sellerInfo;

        public Product withTitle(String title) {
            this.title = title;
            return this;
        }

        public Product withImage(String image) {
            this.image = image;
            return this;
        }

        public Product withPriceHtml(String priceHtml) {
            this.priceHtml = priceHtml;
            return this;
        }

        public Product withPrice(String price) {
            this.price = price;
            return this;
        }

        public Product withRating(String rating) {
            this.rating = rating;
            return this;
        }

        public Product withAppThumbnail(String appThumbnail) {
            this.appThumbnail = appThumbnail;
            return this;
        }

        public Product withId(String id) {
            this.id = id;
            return this;
        }

        public Product withName(String name) {
            this.name = name;
            return this;
        }

        public Product withSlug(String slug) {
            this.slug = slug;
            return this;
        }

        public Product withPermalink(String permalink) {
            this.permalink = permalink;
            return this;
        }

        public Product withDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Product withDateCreatedGmt(String dateCreatedGmt) {
            this.dateCreatedGmt = dateCreatedGmt;
            return this;
        }

        public Product withDateModified(String dateModified) {
            this.dateModified = dateModified;
            return this;
        }

        public Product withDateModifiedGmt(String dateModifiedGmt) {
            this.dateModifiedGmt = dateModifiedGmt;
            return this;
        }

        public Product withType(String type) {
            this.type = type;
            return this;
        }

        public Product withStatus(String status) {
            this.status = status;
            return this;
        }

        public Product withFeatured(boolean featured) {
            this.featured = featured;
            return this;
        }

        public Product withCatalogVisibility(String catalogVisibility) {
            this.catalogVisibility = catalogVisibility;
            return this;
        }

        public Product withDescription(String description) {
            this.description = description;
            return this;
        }

        public Product withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Product withSku(String sku) {
            this.sku = sku;
            return this;
        }

        public Product withTaxPrice(String taxPrice) {
            this.taxPrice = taxPrice;
            return this;
        }

        public Product withPriceExcludingTax(String priceExcludingTax) {
            this.priceExcludingTax = priceExcludingTax;
            return this;
        }

        public Product withPriceIncludingTax(String priceIncludingTax) {
            this.priceIncludingTax = priceIncludingTax;
            return this;
        }

        public Product withRegularPrice(String regularPrice) {
            this.regularPrice = regularPrice;
            return this;
        }

        public Product withSalePrice(String salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Product withDateOnSaleFrom(Object dateOnSaleFrom) {
            this.dateOnSaleFrom = dateOnSaleFrom;
            return this;
        }

        public Product withDateOnSaleFromGmt(Object dateOnSaleFromGmt) {
            this.dateOnSaleFromGmt = dateOnSaleFromGmt;
            return this;
        }

        public Product withDateOnSaleTo(Object dateOnSaleTo) {
            this.dateOnSaleTo = dateOnSaleTo;
            return this;
        }

        public Product withDateOnSaleToGmt(Object dateOnSaleToGmt) {
            this.dateOnSaleToGmt = dateOnSaleToGmt;
            return this;
        }

        public Product withOnSale(boolean onSale) {
            this.onSale = onSale;
            return this;
        }

        public Product withPurchasable(boolean purchasable) {
            this.purchasable = purchasable;
            return this;
        }

        public Product withTotalSales(String totalSales) {
            this.totalSales = totalSales;
            return this;
        }

        public Product withVirtual(boolean virtual) {
            this.virtual = virtual;
            return this;
        }

        public Product withDownloadable(boolean downloadable) {
            this.downloadable = downloadable;
            return this;
        }


        public Product withDownloadLimit(int downloadLimit) {
            this.downloadLimit = downloadLimit;
            return this;
        }

        public Product withDownloadExpiry(int downloadExpiry) {
            this.downloadExpiry = downloadExpiry;
            return this;
        }

        public Product withExternalUrl(String externalUrl) {
            this.externalUrl = externalUrl;
            return this;
        }

        public Product withButtonText(String buttonText) {
            this.buttonText = buttonText;
            return this;
        }

        public Product withTaxStatus(String taxStatus) {
            this.taxStatus = taxStatus;
            return this;
        }

        public Product withTaxClass(String taxClass) {
            this.taxClass = taxClass;
            return this;
        }

        public Product withManageStock(boolean manageStock) {
            this.manageStock = manageStock;
            return this;
        }

        public Product withStockQuantity(Object stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Product withInStock(boolean inStock) {
            this.inStock = inStock;
            return this;
        }

        public Product withBackorders(String backorders) {
            this.backorders = backorders;
            return this;
        }

        public Product withBackordersAllowed(boolean backordersAllowed) {
            this.backordersAllowed = backordersAllowed;
            return this;
        }

        public Product withBackordered(boolean backordered) {
            this.backordered = backordered;
            return this;
        }

        public Product withSoldIndividually(boolean soldIndividually) {
            this.soldIndividually = soldIndividually;
            return this;
        }

        public Product withWeight(String weight) {
            this.weight = weight;
            return this;
        }

        public Product withDimensions(Dimensions dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Product withShippingRequired(boolean shippingRequired) {
            this.shippingRequired = shippingRequired;
            return this;
        }

        public Product withShippingTaxable(boolean shippingTaxable) {
            this.shippingTaxable = shippingTaxable;
            return this;
        }

        public Product withShippingClass(String shippingClass) {
            this.shippingClass = shippingClass;
            return this;
        }

        public Product withShippingClassId(int shippingClassId) {
            this.shippingClassId = shippingClassId;
            return this;
        }

        public Product withReviewsAllowed(boolean reviewsAllowed) {
            this.reviewsAllowed = reviewsAllowed;
            return this;
        }

        public Product withAverageRating(String averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public Product withRatingCount(int ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public Product withRelatedIds(List<Integer> relatedIds) {
            this.relatedIds = relatedIds;
            return this;
        }

        public Product withUpsellIds(List<Object> upsellIds) {
            this.upsellIds = upsellIds;
            return this;
        }

        public Product withCrossSellIds(List<Object> crossSellIds) {
            this.crossSellIds = crossSellIds;
            return this;
        }

        public Product withParentId(int parentId) {
            this.parentId = parentId;
            return this;
        }

        public Product withPurchaseNote(String purchaseNote) {
            this.purchaseNote = purchaseNote;
            return this;
        }

        public Product withCategories(List<Category> categories) {
            this.categories = categories;
            return this;
        }

        public Product withTags(List<Object> tags) {
            this.tags = tags;
            return this;
        }

        public Product withImages(List<CategoryList.Image> images) {
            this.images = images;
            return this;
        }

        public Product withAttributes(List<CategoryList.Attribute> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Product withDefaultAttributes(List<Object> defaultAttributes) {
            this.defaultAttributes = defaultAttributes;
            return this;
        }

        public Product withVariations(List<Object> variations) {
            this.variations = variations;
            return this;
        }

        public Product withGroupedProducts(List<Object> groupedProducts) {
            this.groupedProducts = groupedProducts;
            return this;
        }

        public Product withMenuOrder(int menuOrder) {
            this.menuOrder = menuOrder;
            return this;
        }

     /*   public Product withMetaData(List<MetaDatum> metaData) {
            this.metaData = metaData;
            return this;
        }*/

        public Product withRewardsMessage(String rewardsMessage) {
            this.rewardsMessage = rewardsMessage;
            return this;
        }

        public Product withAdditionInfoHtml(String additionInfoHtml) {
            this.additionInfoHtml = additionInfoHtml;
            return this;
        }

        public Product withFeaturedVideo(FeaturedVideo featuredVideo) {
            this.featuredVideo = featuredVideo;
            return this;
        }

        public Product withPercentage(int percentage) {
            this.percentage = percentage;
            return this;
        }
    }

    public class Category {

        @SerializedName("id")
        @Expose
        public int    id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("slug")
        @Expose
        public String slug;

        public Category withId(int id) {
            this.id = id;
            return this;
        }

        public Category withName(String name) {
            this.name = name;
            return this;
        }

        public Category withSlug(String slug) {
            this.slug = slug;
            return this;
        }

    }

    public class Dimensions {

        @SerializedName("length")
        @Expose
        public String length;
        @SerializedName("width")
        @Expose
        public String width;
        @SerializedName("height")
        @Expose
        public String height;

        public Dimensions withLength(String length) {
            this.length = length;
            return this;
        }

        public Dimensions withWidth(String width) {
            this.width = width;
            return this;
        }

        public Dimensions withHeight(String height) {
            this.height = height;
            return this;
        }

    }

    public class MetaDatum {

        @SerializedName("id")
        @Expose
        public int    id;
        @SerializedName("key")
        @Expose
        public String key;
        @SerializedName("value")
        @Expose
        public String value;

        public MetaDatum withId(int id) {
            this.id = id;
            return this;
        }

        public MetaDatum withKey(String key) {
            this.key = key;
            return this;
        }

        public MetaDatum withValue(String value) {
            this.value = value;
            return this;
        }

    }


    public class FeaturedVideo {

        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("product_id")
        @Expose
        public int    productId;
        @SerializedName("video_type")
        @Expose
        public String videoType;
        @SerializedName("video_id")
        @Expose
        public String videoId;
        @SerializedName("image_url")
        @Expose
        public String imageUrl;


    }

    public class ProductsCarousel {

        @SerializedName("feature_products")
        @Expose
        public FeatureProducts     featureProducts;
        @SerializedName("recent_products")
        @Expose
        public RecentProducts      recentProducts;
        @SerializedName("special_deal_products")
        @Expose
        public SpecialDealProducts specialDealProducts;
        @SerializedName("popular_products")
        @Expose
        public PopularProducts     popularProducts;
        @SerializedName("top_rated_products")
        @Expose
        public TopRatedProducts    topRatedProducts;

        public ProductsCarousel withFeatureProducts(FeatureProducts featureProducts) {
            this.featureProducts = featureProducts;
            return this;
        }

        public ProductsCarousel withRecentProducts(RecentProducts recentProducts) {
            this.recentProducts = recentProducts;
            return this;
        }

        public ProductsCarousel withTopRatedProducts(TopRatedProducts topRatedProducts) {
            this.topRatedProducts = topRatedProducts;
            return this;
        }

        public ProductsCarousel withSpecialDealProducts(SpecialDealProducts specialDealProducts) {
            this.specialDealProducts = specialDealProducts;
            return this;
        }

        public ProductsCarousel withPopularProducts(PopularProducts popularProducts) {
            this.popularProducts = popularProducts;
            return this;
        }

    }

    public class TopRatedProducts {

        @SerializedName("status")
        @Expose
        public String        status;
        @SerializedName("title")
        @Expose
        public String        title;
        @SerializedName("screen_order")
        @Expose
        public int           screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public TopRatedProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public TopRatedProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public TopRatedProducts withScreenOrder(int screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public TopRatedProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }
    }

    public class RecentProducts {

        @SerializedName("status")
        @Expose
        public String        status;
        @SerializedName("title")
        @Expose
        public String        title;
        @SerializedName("orderby")
        @Expose
        public String        orderby;
        @SerializedName("order")
        @Expose
        public String        order;
        @SerializedName("screen_order")
        @Expose
        public Integer       screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public RecentProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public RecentProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public RecentProducts withOrderby(String orderby) {
            this.orderby = orderby;
            return this;
        }

        public RecentProducts withOrder(String order) {
            this.order = order;
            return this;
        }

        public RecentProducts withScreenOrder(Integer screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public RecentProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }


    public class SpecialDealProducts {

        @SerializedName("status")
        @Expose
        public String        status;
        @SerializedName("title")
        @Expose
        public String        title;
        @SerializedName("orderby")
        @Expose
        public String        orderby;
        @SerializedName("order")
        @Expose
        public String        order;
        @SerializedName("screen_order")
        @Expose
        public Integer       screenOrder;
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public SpecialDealProducts withStatus(String status) {
            this.status = status;
            return this;
        }

        public SpecialDealProducts withTitle(String title) {
            this.title = title;
            return this;
        }

        public SpecialDealProducts withOrderby(String orderby) {
            this.orderby = orderby;
            return this;
        }

        public SpecialDealProducts withOrder(String order) {
            this.order = order;
            return this;
        }

        public SpecialDealProducts withScreenOrder(Integer screenOrder) {
            this.screenOrder = screenOrder;
            return this;
        }

        public SpecialDealProducts withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

    }

    public class StaticPage {

        @SerializedName("about_us")
        @Expose
        public String aboutUs;
        @SerializedName("terms_of_use")
        @Expose
        public String termsOfUse;
        @SerializedName("privacy_policy")
        @Expose
        public String privacyPolicy;

        public StaticPage withAboutUs(String aboutUs) {
            this.aboutUs = aboutUs;
            return this;
        }

        public StaticPage withTermsOfUse(String termsOfUse) {
            this.termsOfUse = termsOfUse;
            return this;
        }

        public StaticPage withPrivacyPolicy(String privacyPolicy) {
            this.privacyPolicy = privacyPolicy;
            return this;
        }

    }

    public class ProductsViewOrder {

        @SerializedName("name")
        @Expose
        public String name;

    }

    public class WebViewPages {
        @SerializedName("web_view_pages_page_title")
        @Expose
        public String webViewPagesPageTitle;
        @SerializedName("web_view_pages_page_id")
        @Expose
        public String webViewPagesPageId;

        public WebViewPages withWebViewPagesPageTitle(String webViewPagesPageTitle) {
            this.webViewPagesPageTitle = webViewPagesPageTitle;
            return this;
        }

        public WebViewPages withWebViewPagesPageId(String webViewPagesPageId) {
            this.webViewPagesPageId = webViewPagesPageId;
            return this;
        }
    }

    public class WpmlLanguage {

        @SerializedName("code")
        @Expose
        public String  code;
        @SerializedName("id")
        @Expose
        public String  id;
        @SerializedName("native_name")
        @Expose
        public String  nativeName;
        @SerializedName("active")
        @Expose
        public int     active;
        @SerializedName("default_locale")
        @Expose
        public String  defaultLocale;
        @SerializedName("translated_name")
        @Expose
        public String  translatedName;
        @SerializedName("language_code")
        @Expose
        public String  languageCode;
        @SerializedName("disp_language")
        @Expose
        public String  dispLanguage;
        @SerializedName("site_language")
        @Expose
        public String  siteLanguage;
        @SerializedName("is_rtl")
        @Expose
        public boolean isRtl;

        public WpmlLanguage withCode(String code) {
            this.code = code;
            return this;
        }

        public WpmlLanguage withId(String id) {
            this.id = id;
            return this;
        }

        public WpmlLanguage withNativeName(String nativeName) {
            this.nativeName = nativeName;
            return this;
        }

        public WpmlLanguage withActive(int active) {
            this.active = active;
            return this;
        }

        public WpmlLanguage withDefaultLocale(String defaultLocale) {
            this.defaultLocale = defaultLocale;
            return this;
        }

        public WpmlLanguage withTranslatedName(String translatedName) {
            this.translatedName = translatedName;
            return this;
        }

        public WpmlLanguage withLanguageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public WpmlLanguage withDispLanguage(String dispLanguage) {
            this.dispLanguage = dispLanguage;
            return this;
        }

        public WpmlLanguage withSiteLanguage(String siteLanguage) {
            this.siteLanguage = siteLanguage;
            return this;
        }

        public WpmlLanguage withIsRtl(boolean isRtl) {
            this.isRtl = isRtl;
            return this;
        }
    }
}