import { React, useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";

import Button from "../../components/button/Button";
import Input from "../../components/input/Input";

import {
  ButtonWrapper,
  FormWrapper,
  InputRow,
  InputWrapper,
} from "../../styles/FormStyles";

import { purchaseDetailsFormInfo } from "../../utils/form-info/transaction-details.forminfo";

import { useMediaQuery } from "@mui/material";
import { endpoints } from "../../utils/endpoints/endpoints";
import { clearFormFields } from "../../utils/functions/formUtils";

import _ from "lodash";

const TransactionDetails = () => {
  const transactionsTab = useSelector((state) => state.transactionsTab);
  const [formInfo, setFormInfo] = useState([]);
  const [transactionCompletionOk, setTransactionCompletionOk] = useState(false);
  const isNotAPhone = useMediaQuery("(min-width: 1000px)");
  const formRef = useRef(null);

  const formInfoMap = {
    purchase: purchaseDetailsFormInfo,
    // transfer: transferFormInfo,
    // sale: sellFormInfo,
  };

  useEffect(() => {
    setFormInfo(formInfoMap[transactionsTab] || []);
  }, [transactionsTab]);

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    const tabEndpoints = {
      purchase: endpoints.buy.base,
      sale: endpoints.sale.base,
      transfer: endpoints.transfer.base,
    };

    const requestBodyMap = {
      purchase: {
        invoiceCode: formData.get("invoice code"),
        providerName: formData.get("provider"),
      },
      sale: {
        clientName: formData.get("client name"),
      },
    };
    const requestBody = requestBodyMap[transactionsTab];

    await fetch(tabEndpoints[transactionsTab], {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "user-email": user.email,
      },
      body: JSON.stringify(requestBody),
    })
      .then((res) => {
        res.ok
          ? alert(
              `${_.upperFirst(transactionsTab)} has been successfully made.`
            )
          : alert(`There was an error processing the ${transactionsTab}`);
        setTransactionInitOk(true);
        clearFormFields(formRef);
      })
      .catch((error) => {
        console.error("Error saving the " + transactionsTab, error);
        alert(`There was an error processing the ${transactionsTab}.`);
      });
  };

  return (
    <FormWrapper>
      <form ref={formRef} onSubmit={handleFormSubmit}>
        <InputRow>
          {formInfo.map(({ title, description, type }) => (
            <InputWrapper key={title}>
              <Input title={title} description={description} type={type} />
            </InputWrapper>
          ))}
        </InputRow>
        <ButtonWrapper isNotAPhone={isNotAPhone}>
          <Button type="submit" text="Save" />
        </ButtonWrapper>
      </form>
    </FormWrapper>
  );
};

export default TransactionDetails;
