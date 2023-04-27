import styled from "styled-components";

export const GestionContentWrapper = styled.div`
  padding-bottom: 20px;
`;

export const GestionContentHeader = styled.h2`
  font-size: 1.5rem;
  line-height: 2rem;
  font-weight: 500;
  padding: 1.25rem;
`;

export const GestionContentFormWrapper = styled.div`
  margin: 0 1rem;
  border: 1px solid #b3c3d3;
  border-radius: 0.375rem;
  @media (min-width: 768px) {
    border: 1px solid lightblue;
    max-width: 70%;
    border-radius: 0.375rem;
  }
`;

export const GestionContentButtonWrapper = styled.div`
  display: flex;
  justify-content: flex-end;
  align-items: center;
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
