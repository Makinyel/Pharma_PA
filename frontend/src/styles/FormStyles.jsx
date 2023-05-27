import styled from "styled-components";

export const Wrapper = styled.div`
  padding-bottom: 10rem;
`;

export const Container = styled.div`
  display: flex;
  @media (max-width: 768px) {
    display: block;
  }
`;

export const Header = styled.h2`
  font-size: 1.5rem;
  line-height: 2rem;
  font-weight: 500;
  padding: 1.25rem;
`;

export const Subtitle = styled.h3`
  color: #0f0f0f;
  font-size: 1.15rem;
  padding: 1rem 1.25rem;
`;

export const InputRow = styled.div`
  display: flex;
  flex-wrap: wrap;
  //margin: -10px; /* Adjust margin to account for padding on Input components */

  @media (max-width: 768px) {
    display: block;
  }
`;

export const InputWrapper = styled.div`
  flex-basis: 50%;
  padding: 10px;
  box-sizing: border-box;

  @media (max-width: 768px) {
    flex-basis: 100%;
  }
`;

export const FormWrapper = styled.div`
  margin: 0 1rem;
  border: 1px solid #b3c3d3;
  border-radius: 0.375rem;
  flex-basis: 50%;

  @media (min-width: 768px) {
    border: 1px solid lightblue;
    max-width: 70%;
    border-radius: 0.375rem;
  }
`;

export const ButtonWrapper = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 1rem;
  padding: 1rem;
  width: 100%;
  height: 12%;
  background-color: #b3c3d3;

  ${({ isNotAPhone }) =>
    !isNotAPhone &&
    `
    position: fixed;
    bottom: 0;
    left: 0;
  `}
`;
