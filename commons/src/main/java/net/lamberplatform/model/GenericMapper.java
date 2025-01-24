package net.lamberplatform.model;

/**
 * DATE: 2025/1/25
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
public interface GenericMapper<PO, BO> {

    BO po2bo(PO po);
    PO bo2po(BO bo);
}
