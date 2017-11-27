package com.roka.NcpPipe.NcpManager;

import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.product.connection.ProductConnection;
import com.ncloud.api.product.model.ProductList;

import java.util.List;
import java.util.function.Function;

public class ProductManager extends ProductConnection {


    public ProductManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public ProductManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public Function<Object, ProductList> getServerImageProductListForJob(String exclusionProductCode, String productCode, List<String> platformTypeCodeList, Integer blockStorageSize, String regionNo) {
        return (result) -> super.getServerImageProductList(exclusionProductCode, productCode, platformTypeCodeList, blockStorageSize, regionNo);
    }


    public Function<Object, ProductList> getServerProductListForJob(String exclusionProductCode, String productCode, String serverImageProductCode, String regionNo) {
        return (result) -> super.getServerProductList(exclusionProductCode, productCode, serverImageProductCode, regionNo);
    }
}
