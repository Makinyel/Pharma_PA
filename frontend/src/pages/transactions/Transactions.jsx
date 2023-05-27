import { React, useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import {
  formInfoMap,
  detailsFormInfoMap,
} from "../../utils/form-info/map/transactions.forminfomap";

import Button from "../../components/button/Button";
import Input from "../../components/input/Input";
import Table from "../../components/Table.jsx";

import {
  ButtonWrapper,
  Container,
  FormWrapper,
  Header,
  InputRow,
  InputWrapper,
  Subtitle,
  Wrapper,
} from "../../styles/FormStyles";

import { useMediaQuery } from "@mui/material";
import { endpoints } from "../../utils/endpoints/endpoints";
import {
  detailsEndpoints,
  tabEndpoints,
} from "../../utils/endpoints/transaction.endpoints";
import { clearFormFields } from "../../utils/functions/formUtils";

import _ from "lodash";
import {
  setTransaction,
  setTransactionDetails,
  setTransactionDetailsProducts,
  toggleTransactionInitiated,
  untoggleTransactionInitiated,
} from "../../state/state.js";

const Transactions = () => {
  const dispatch = useDispatch();
  const user = useSelector((state) => state.user);
  const module = useSelector((state) => state.currentModule);
  const transactionsTab = useSelector((state) => state.transactionsTab);
  const transaction = useSelector((state) => state.transaction);
  const transactionDetails = useSelector((state) => state.transactionDetails);
  const transactionInitiated = useSelector(
    (state) => state.transactionInitiated
  );
  const transactionDetailsProducts = useSelector(
    (state) => state.transactionDetailsProducts
  );
  const [formInfo, setFormInfo] = useState([]);
  const [detailsFormInfo, setDetailsFormInfo] = useState([]);
  const [stepCount, setStepCount] = useState(1);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const formRef = useRef(null);

  useEffect(() => {
    setFormInfo(formInfoMap[transactionsTab] || []);
    dispatch(untoggleTransactionInitiated());
    dispatch(setTransaction({}));
    dispatch(setTransactionDetails([]));
    dispatch(setTransactionDetailsProducts([]));
    setStepCount(1);
  }, [transactionsTab, module]);

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    const requestInitBodyMap = {
      purchase: {
        invoiceCode: formData.get("invoice code"),
        providerName: formData.get("provider"),
      },
      sale: {
        clientName: formData.get("client"),
      },
    };

    const requestBody = requestInitBodyMap[transactionsTab];

    const response = await fetch(tabEndpoints[transactionsTab], {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "user-email": user.email,
      },
      body: JSON.stringify(requestBody),
    });

    if (!response.ok) {
      alert(`There was an error processing the ${transactionsTab}`);
      return;
    }

    const savedTransactionInit = await response.json();

    alert(`${_.upperFirst(transactionsTab)} has been successfully made.`);

    dispatch(toggleTransactionInitiated());
    setStepCount(stepCount + 1);
    dispatch(setTransaction({ transaction: savedTransactionInit }));
    setDetailsFormInfo(detailsFormInfoMap[transactionsTab]);
    clearFormFields(formRef);
  };

  const addDetails = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    //save product to table
    const product = await fetch(
      endpoints.product.getByName(formData.get("product name"))
    );
    const productData = await product.json();
    dispatch(setTransactionDetailsProducts([productData]));

    const requestDetailsMap = {
      purchase: {
        purchaseId: transaction?.id,
        quantity: formData.get("quantity"),
        destinationWarehouseName: formData.get("destination warehouse"),
        productName: formData.get("product name"),
      },
      sale: {
        saleId: transaction?.id,
        quantity: formData.get("quantity"),
        origenwarehouseName: formData.get("source warehouse"),
        productName: formData.get("product name"),
      },
    };

    //1. add detail to list of details
    dispatch(setTransactionDetails([requestDetailsMap[transactionsTab]]));

    alert(`${_.upperFirst(transactionsTab)} detail has been added.`);
    clearFormFields(formRef);
  };

  const completeTransaction = async () => {
    //2. POST list of details
    const response = await fetch(detailsEndpoints[transactionsTab], {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(transactionDetails),
    });

    if (!response.ok) {
      alert(`There was an error processing the ${transactionsTab}`);
      return;
    }

    const savedTransactionDetail = await response.json();
    console.log(savedTransactionDetail);

    if (!savedTransactionDetail.ok) {
      alert(`There was something wrong completing the transaction`);
      return;
    }

    alert(`${_.upperFirst(transactionsTab)} has completed!`);
    dispatch(toggleTransactionInitiated());
    dispatch(setTransactionDetails([]));
    dispatch(setTransactionDetailsProducts([]));
    setStepCount(1);
    setFormInfo(formInfoMap[transactionsTab]);
    clearFormFields(formRef);
  };

  return (
    <Wrapper>
      <Header>{`${_.upperFirst(transactionsTab)} a product`}</Header>
      <Subtitle>Step {stepCount}/2</Subtitle>
      <Container>
        <FormWrapper>
          <form
            ref={formRef}
            onSubmit={transactionInitiated ? addDetails : handleFormSubmit}
          >
            {transactionInitiated ? (
              <InputRow>
                {detailsFormInfo.map(({ title, description, type }) => (
                  <InputWrapper key={title}>
                    <Input
                      title={title}
                      description={description}
                      type={type}
                    />
                  </InputWrapper>
                ))}
              </InputRow>
            ) : (
              <InputRow>
                {formInfo.map(({ title, description, type }) => (
                  <InputWrapper key={title}>
                    <Input
                      title={title}
                      description={description}
                      type={type}
                    />
                  </InputWrapper>
                ))}
              </InputRow>
            )}
            <ButtonWrapper isNotAPhone={isNotAPhone}>
              <Button
                type="submit"
                text={transactionInitiated ? "Add detail" : "Save"}
              />
              {transactionInitiated && (
                <Button
                  type="button"
                  text="Complete purchase"
                  onClick={completeTransaction}
                />
              )}
            </ButtonWrapper>
          </form>
        </FormWrapper>
        {transactionInitiated && (
          <div className="p-[1.25rem]">
            <h3 className="text-xl font-medium">
              {_.upperFirst(transactionsTab)} details
            </h3>
            <h4>Products added</h4>
            <Table tableData={transactionDetailsProducts} />
          </div>
        )}
      </Container>
    </Wrapper>
  );
};

export default Transactions;
