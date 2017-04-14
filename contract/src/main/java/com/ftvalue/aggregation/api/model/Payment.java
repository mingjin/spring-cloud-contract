package com.ftvalue.aggregation.api.model;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.TreeMap;

public class Payment {

    private final Map<String, String> map = new TreeMap<>();
    private String secureCode = StringUtils.EMPTY;

    public Payment secureCode(String secureCode) {
        this.secureCode = secureCode;
        return this;
    }

    public String getSignature() {
        return DigestUtils.md5Hex(this.toString().concat(this.secureCode));
    }

    public Payment set(String fieldName, String fieldValue) {
        this.map.put(fieldName, fieldValue);
        return this;
    }

    public Payment set(String fieldName, int fieldValue) {
        this.map.put(fieldName, Integer.toString(fieldValue));
        return this;
    }

    public Payment set(String fieldName, Float fieldValue) {
        this.map.put(fieldName, Float.toString(fieldValue));
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            final String value = entry.getValue();
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            builder.append(String.format("%s=%s&", entry.getKey(), value));
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    public MultiValueMap<String, String> toQueryParams() {
        final MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(map);
        multiValueMap.add("sign", this.getSignature());
        multiValueMap.add("sign_type", "MD5");
        return multiValueMap;
    }
}
