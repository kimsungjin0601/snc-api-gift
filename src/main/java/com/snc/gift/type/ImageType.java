package com.snc.gift.type;

import com.snc.gift.vo.CodeVo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ImageType {
    MAIN("MAIN", "","메인"),
    LIST("LIST", "","리스트"),
    DETAIL("DETAIL", "","상세"),
    THUMBNAIL("THUMBNAIL", "","썸네일"),
    ZOOM("THUMBNAIL", "","확대 이미지");

    private final String code;
    private final String msgKey;
    private final String name;

    private static final Map<String, String> codeMap =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ImageType::getCode, ImageType::name)));

    public static ImageType of(final String code){
        return ImageType.valueOf(codeMap.get(code));
    }

    public static String getName(final String code, final MessageSource messageSource){
        String msgKey = valueOf(codeMap.get(code)).msgKey;
        return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
    }

    public static String getName(final String code){
        Locale locale = LocaleContextHolder.getLocale();
        if(locale.getLanguage().equals("ko")){
            return valueOf(codeMap.get(code)).name;
        } else if(locale.getLanguage().equals("en")){
            return valueOf(codeMap.get(code)).name;
        } else {
            return valueOf(codeMap.get(code)).name;
        }
    }

    public static List<CodeVo> getList(){
        List<CodeVo> types = new ArrayList<>();
        Locale locale = LocaleContextHolder.getLocale();
        Stream.of(values()).forEach(v->{
            if(locale.getLanguage().equals("ko")){
                types.add(CodeVo.builder().code(v.getCode()).name(v.getName()).build());
            } else if(locale.getLanguage().equals("en")){
                types.add(CodeVo.builder().code(v.getCode()).name(v.getName()).build());
            } else {
                types.add(CodeVo.builder().code(v.getCode()).name(v.getName()).build());
            }
        });
        return types;
    }

    public static List<CodeVo> getList(final MessageSource messageSource){
        List<CodeVo> types = new ArrayList<>();
        Stream.of(values()).forEach(v->{
            String msgKey = valueOf(codeMap.get(v.getCode())).msgKey;
            String msg = messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
            types.add(CodeVo.builder().code(v.getCode()).name(msg).build());
        });
        return types;
    }
}
